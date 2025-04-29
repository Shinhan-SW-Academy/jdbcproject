package com.shinhan.controller;

import com.shinhan.common.CommonInterface;
import com.shinhan.common.ControllerFactory;
import com.shinhan.common.OrderInterface;
import com.shinhan.dto.BusinessDTO;
import com.shinhan.service.BusinessService;
import com.shinhan.view.UserView;

import java.util.Scanner;

public class BusinessController implements CommonInterface {
    Scanner sc = new Scanner(System.in);
    BusinessService businessService = new BusinessService();
    UserView userView = new UserView();

    @Override
    public void execute() {
        boolean isStop = false;
        OrderInterface controller = null;
        BusinessDTO business = null;

        while(!isStop) {
            userView.signDisplay();
            int job = sc.nextInt();
            sc.nextLine();

            if(job == 3) {
                isStop = true;
                continue;
            }

            business = sign(job);
            if(business == null) {
                continue;
            }

            while(!isStop) {
                userView.businessDisplay();
                job = sc.nextInt();
                sc.nextLine();

                switch (job) {
                    case 4 -> f_updateUser(business); // 회원 정보 수정
                    case 5 -> {
                        f_deleteUser(business); // 회원 탈퇴
                        isStop = true; // 탈퇴 후 종료
                    }
                    case 6 -> {
                        System.out.println("============= 로그아웃 =============");
                        isStop = true;
                    }
                    default -> {
                        controller = ControllerFactory.business(job);
                        if(controller == null) {
                            continue;
                        }
                        controller.execute(business);
                    }
                }
            }
        }
    }

    private void f_deleteUser(BusinessDTO business) {
        businessService.deleteById(business.getBusiness_id());
        userView.display("회원 탈퇴가 완료되었습니다.");
    }

    private void f_updateUser(BusinessDTO business) {
        businessService.updateById(business);
        userView.display("회원 정보가 수정되었습니다.");
    }

    @Override
    public BusinessDTO sign(int job) {
        BusinessDTO business = null;
        switch(job) {
            case 1 -> {
                business = f_signUp();
                if(business == null) {
                    userView.display("============= 회원가입 실패 =============");
                    return null;
                }
            }
            case 2 -> {
                business = f_signIn();
                if(business == null) {
                    userView.display("============= 로그인 실패 =============");
                    return null;
                }
            }
            default -> business = null;
        }
        return business;
    }

    private BusinessDTO f_signIn() {
        System.out.println("============= 회원 로그인 =============");
        System.out.printf("아이디: ");
        String id = sc.nextLine();
        System.out.printf("비밀번호: ");
        String pw = sc.nextLine();

        BusinessDTO business = businessService.login(id, pw);

        return business;
    }

    private BusinessDTO f_signUp() {
        System.out.println("============= 회원 회원가입 =============");
        System.out.printf("아이디: ");
        String id = sc.nextLine();

        BusinessDTO existBusiness = businessService.selectById(id);
        if(existBusiness != null) {
            userView.display("이미 존재하는 아이디입니다.");
            return null;
        }

        BusinessDTO business = businessService.insertBusiness(makeBusiness(id));
        userView.display("============= 회원가입 성공 =============");

        return business;
    }

    private BusinessDTO makeBusiness(String id) {
        System.out.printf("비밀번호: ");
        String pw = sc.nextLine();
        System.out.printf("이름: ");
        String name = sc.nextLine();
//        System.out.printf("계좌번호: ");
//        String account = sc.nextLine();

        if(pw.equals("0")) pw = null;
        if(name.equals("0")) name = null;
//        if(account.equals("0")) account = null;

        BusinessDTO business = BusinessDTO.builder()
                .business_id(id)
                .business_pw(pw)
                .business_name(name)
                .build();

        return business;
    }
}
