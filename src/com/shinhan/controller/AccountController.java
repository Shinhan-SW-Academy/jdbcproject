package com.shinhan.controller;

import com.shinhan.common.OrderInterface;
import com.shinhan.dto.*;
import com.shinhan.service.AccountService;
import com.shinhan.service.OrderService;
import com.shinhan.view.AccountView;
import com.shinhan.view.OrderView;

import java.util.List;
import java.util.Scanner;

public class AccountController implements OrderInterface {
    Scanner sc = new Scanner(System.in);
    AccountService accountService = new AccountService();
    OrderService orderService = new OrderService();
    AccountView accountView = new AccountView();
    OrderView orderView = new OrderView();

    @Override
    public void execute(Object obj) {
        boolean isStop = false;
        int job = 0;

        while(!isStop) {
            if(obj instanceof UserDTO user) {
                accountView.userDisplay();
                job = sc.nextInt();
                sc.nextLine();

                switch(job) {
                    case 1 -> {
                        // 잔액 확인
                        f_selectuAcc(user.getUser_id());
                    }
                    case 2 -> {
                        // 포인트 충전
                        f_deposit(user.getUser_id());
                    }
                    case 3 -> {
                        isStop = true;
                    }
                    case 4 -> {
                        System.exit(0);
                    }
                }
            } else if(obj instanceof BusinessDTO business) {
                accountView.businessDisplay();
                job = sc.nextInt();
                sc.nextLine();

                switch(job) {
                    case 1 -> {
                        // 총 매출 확인
                        f_selectbAcc(business.getBusiness_id());
                    }
                    case 2 -> {
                        // 상품 판매 현황
                        f_orderRank(business.getBusiness_id());
                    }
                    case 3 -> {
                        isStop = true;
                    }
                    case 4 -> {
                        System.exit(0);
                    }
                }
            }
        }
    }

    private void f_selectuAcc(String userId) {
        AccountDTO.UserAccountDTO account = accountService.selectuAccount(userId);
        accountView.displayuAcc(account);
    }

    private void f_selectbAcc(String businessId) {
        AccountDTO.BusinessAccountDTO account = accountService.selectbAccount(businessId);
        accountView.displaybAcc(account);
    }

    private void f_orderRank(String businessId) {
        List<OrderDTO> rankList = orderService.orderRank(businessId);
        orderView.displayRank(rankList);
    }

    private void f_deposit(String userId) {
        System.out.printf("충전 금액 입력> ");
        accountService.deposit(userId, sc.nextInt());
        sc.nextLine();

        accountView.display("============= 포인트 충전 완료 =============");
    }
}
