package com.shinhan.common;

import java.util.Scanner;

public class FrontController {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isStop = false;
        CommonInterface controller = null;

        while(!isStop) {
            menuDisplay();
            int job = sc.nextInt();
            sc.nextLine();

            if(job == 3) {
                System.out.println("============= 프로그램 종료 =============");
                isStop = true;
                continue;
            }

            controller = ControllerFactory.make(job);
            if(controller == null) {
                System.out.println("잘못된 입력입니다.");
                continue;
            }

            controller.execute();
        }
        sc.close();
    }

    public static void menuDisplay() {
        System.out.println("-----------------------------");
        System.out.println("1. 회원 | 2. 사업자 | 3. 종료");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }
}