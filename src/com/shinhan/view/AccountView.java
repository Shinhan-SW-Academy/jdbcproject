package com.shinhan.view;

import com.shinhan.dto.AccountDTO;

public class AccountView {
    public void display(String message) {
        System.out.println(message);
    }

    public void userDisplay() {
        System.out.println("-----------------------------");
        System.out.println("1. 잔액 확인 | 2. 포인트 충전");
        System.out.println("3. 이전 페이지 | 4. 종료");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }

    public void businessDisplay() {
        System.out.println("-----------------------------");
        System.out.println("1. 총 매출 확인 | 2. 상품 판매 현황");
        System.out.println("3. 이전 페이지 | 4. 종료");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }

    public void displayuAcc(AccountDTO.UserAccountDTO account) {
        System.out.printf("[%s님] 잔액 %d원\n", account.getAccount_name(), account.getBalance());
    }

    public void displaybAcc(AccountDTO.BusinessAccountDTO account) {
        System.out.printf("[%s] 총 매출 %d원\n", account.getAccount_name(), account.getBalance());
    }
}
