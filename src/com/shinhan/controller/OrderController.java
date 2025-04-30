package com.shinhan.controller;

import com.shinhan.common.OrderInterface;
import com.shinhan.dto.BusinessDTO;
import com.shinhan.dto.OrderDTO;
import com.shinhan.dto.UserDTO;
import com.shinhan.service.OrderService;
import com.shinhan.view.OrderView;

import java.util.List;
import java.util.Scanner;

public class OrderController implements OrderInterface {
    Scanner sc = new Scanner(System.in);
    OrderService orderService = new OrderService();
    OrderView orderView = new OrderView();

    @Override
    public void execute(Object obj) {
        boolean isStop = false;
        int job = 0;

        while(!isStop) {
            if(obj instanceof UserDTO user) {
                orderView.uOrderDisplay();
                job = sc.nextInt();
                sc.nextLine();

                switch(job) {
                    case 1 -> {
                        // 주문 조회
                        f_selectByUser(user);
                    }
                    case 2 -> {
                        // 주문 취소
                        f_updateById();
                    }
                    case 3 -> {
                        // 이전 페이지
                        isStop = true;
                    }
                    case 4 -> {
                        // 프로그램 종료
                        System.exit(0);
                    }
                }
            } else if(obj instanceof BusinessDTO business) {
                orderView.bOrderDisplay();
                job = sc.nextInt();
                sc.nextLine();

                switch(job) {
                    case 1 -> {
                        // 주문 조회
                        f_selectByBusiness(business);
                    }
                    case 2 -> {
                        // 주문 취소
                        f_updateById();
                    }
                    case 3 -> {
                        // 이전 페이지
                        isStop = true;
                    }
                    case 4 -> {
                        // 프로그램 종료
                        System.exit(0);
                    }
                }
            }
        }
    }

    private void f_selectByUser(UserDTO user) {
        List<OrderDTO> orderList = orderService.selectByUser(user.getUser_id());
        orderView.displayOrder(orderList);
    }

    private void f_selectByBusiness(BusinessDTO business) {
        List<OrderDTO> orderList = orderService.selectByBusiness(business.getBusiness_id());
        orderView.displayOrder(orderList);
    }

    private void f_updateById() {
        System.out.printf("주문 번호 입력> ");
        int orderId = sc.nextInt();
        sc.nextLine();

        OrderDTO order = orderService.selectById(orderId);
        if(order == null) {
            orderView.displayOrder(order);
            return;
        }

        orderService.updateById(orderId);
        orderView.display("============= 주문 취소 완료 =============");
    }
}
