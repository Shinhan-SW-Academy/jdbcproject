package com.shinhan.controller;

import com.shinhan.common.CommonInterface;

import java.util.Scanner;

import static com.shinhan.common.ControllerFactory.*;

public class UserController implements CommonInterface {
    Scanner sc = new Scanner(System.in);

    @Override
    public void execute() {
        boolean isStop = false;
        CommonInterface controller = null;

        while(!isStop) {
            signDisplay();
            int job = sc.nextInt();
            sc.nextLine();

            if(job == 3) {
                isStop = true;
                continue;
            }
            sign(job);
        }
    }

    @Override
    public void sign(int job) {
        switch(job) {
            case 1 -> f_signUp();
            case 2 -> f_signIn();
        }
    }

    private void f_signIn() {

    }

    private void f_signUp() {
        System.out.println("===== 회원 회원가입 =====");
        System.out.printf("아이디: ");
        String id = sc.nextLine();
        System.out.printf("비밀번호: ");
        String pw = sc.nextLine();
        System.out.printf("이름: ");
        String name = sc.nextLine();
        System.out.printf("전화번호: ");
        String phone = sc.nextLine();
        System.out.printf("주소: ");
        String address = sc.nextLine();


    }
}
