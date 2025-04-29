package com.shinhan.common;

import com.shinhan.controller.BusinessController;
import com.shinhan.controller.UserController;

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

    public static void signDisplay() {
        System.out.println("-----------------------------");
        System.out.println("1. 회원가입 | 2. 로그인 | 3. 홈");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }
}
