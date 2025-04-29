package com.shinhan.controller;

import com.shinhan.common.CommonInterface;

import java.util.Scanner;

import static com.shinhan.common.ControllerFactory.*;

public class BusinessController implements CommonInterface {
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
        System.out.println("===== 사업자 회원가입 =====");
        System.out.printf("아이디: ");
        String id = sc.nextLine();
        System.out.printf("비밀번호: ");
        String pw = sc.nextLine();
        System.out.printf("상호명: ");
        String name = sc.nextLine();


    }
}
