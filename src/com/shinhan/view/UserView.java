package com.shinhan.view;

public class UserView {
    public void display(String message) {
        System.out.println(message);
    }

    public void signDisplay() {
        System.out.println("-----------------------------");
        System.out.println("1. 회원가입 | 2. 로그인 | 3. 홈");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }

    public void userDisplay() {
        System.out.println("-----------------------------");
        System.out.println("1. 상품 조회 | 2. 장바구니 | 3. 주문 조회");
        System.out.println("4. 회원 정보 수정 | 5. 회원 탈퇴 | 6. 로그아웃");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }

    public void businessDisplay() {
        System.out.println("-----------------------------");
        System.out.println("1. 상품 관리 | 2. 주문 관리 | 3. 매출 관리");
        System.out.println("4. 회원 정보 수정 | 5. 회원 탈퇴 | 6. 로그아웃");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }
}
