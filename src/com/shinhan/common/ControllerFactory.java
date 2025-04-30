package com.shinhan.common;

import com.shinhan.controller.*;

public class ControllerFactory {
    public static CommonInterface make(int job) {
        CommonInterface controller = null;

        switch(job) {
            case 1 -> controller = new UserController();
            case 2 -> controller = new BusinessController();
            default -> {return null;}
        }

        return controller;
    }

    public static OrderInterface user(int job) {
        OrderInterface controller = null;

        switch(job) {
            case 1 -> controller = new ProductController(); // 상품 조회, 검색
            case 2 -> controller = new CartController(); // 장바구니, 주문
            case 3 -> controller = new OrderController(); // 주문 조회
            case 4 -> controller = new AccountController(); // 포인트 충전
            default -> {return null;}
        }

        return controller;
    }

    public static OrderInterface business(int job) {
        OrderInterface controller = null;

        switch(job) {
            case 1 -> controller = new ProductController(); // 상품 추가, 수정, 삭제
            case 2 -> controller = new OrderController(); // 주문 조회, 반품
            case 3 -> controller = new AccountController(); // 매출 관리
            default -> {return null;}
        }

        return controller;
    }
}
