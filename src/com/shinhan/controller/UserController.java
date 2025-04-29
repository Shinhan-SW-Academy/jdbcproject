package com.shinhan.controller;

import com.shinhan.common.CommonInterface;
import com.shinhan.common.ControllerFactory;
import com.shinhan.common.OrderInterface;
import com.shinhan.dto.UserDTO;
import com.shinhan.service.UserService;
import com.shinhan.view.UserView;

import java.util.Scanner;

public class UserController implements CommonInterface {
    Scanner sc = new Scanner(System.in);
    UserService userService = new UserService();
    UserView userView = new UserView();

    @Override
    public void execute() {
        boolean isStop = false;
        OrderInterface controller = null;
        UserDTO user = null;

        while(!isStop) {
            userView.signDisplay();
            int job = sc.nextInt();
            sc.nextLine();

            if(job == 3) {
                isStop = true;
                continue;
            }

            user = sign(job);
            if(user == null) {
                continue;
            }

            while(!isStop) {
                userView.userDisplay();
                job = sc.nextInt();
                sc.nextLine();

                switch (job) {
                    case 4 -> f_updateUser(user); // 회원 정보 수정
                    case 5 -> {
                        f_deleteUser(user); // 회원 탈퇴
                        isStop = true; // 탈퇴 후 종료
                    }
                    case 6 -> {
                        System.out.println("============= 로그아웃 =============");
                        isStop = true;
                    }
                    default -> {
                        controller = ControllerFactory.user(job);
                        if(controller == null) {
                            continue;
                        }
                        controller.execute(user);
                    }
                }
            }
        }
    }

    private void f_deleteUser(UserDTO user) {
        userService.deleteById(user.getUser_id());
        userView.display("회원 탈퇴가 완료되었습니다.");
    }

    private void f_updateUser(UserDTO user) {
        userService.updateById(makeUser(user.getUser_id()));
        userView.display("회원 정보가 수정되었습니다.");
        // 회원 정보 출력
    }

    @Override
    public UserDTO sign(int job) {
        UserDTO user = null;
        switch(job) {
            case 1 -> {
                user = f_signUp();
                if(user == null) {
                    userView.display("============= 회원가입 실패 =============");
                    return null;
                }
            }
            case 2 -> {
                user = f_signIn();
                if(user == null) {
                    userView.display("============= 로그인 실패 =============");
                    return null;
                }
            }
            default -> user = null;
        }
        return user;
    }

    private UserDTO f_signIn() {
        System.out.println("============= 회원 로그인 =============");
        System.out.printf("아이디: ");
        String id = sc.nextLine();
        System.out.printf("비밀번호: ");
        String pw = sc.nextLine();

        UserDTO user = userService.login(id, pw);

        return user;
    }

    private UserDTO f_signUp() {
        System.out.println("============= 회원 회원가입 =============");
        System.out.printf("아이디: ");
        String id = sc.nextLine();

        UserDTO existUser = userService.selectById(id);
        if(existUser != null) {
            userView.display("이미 존재하는 아이디입니다.");
            return null;
        }

        UserDTO user = userService.insertUser(makeUser(id));
        userView.display("============= 회원가입 성공 =============");

        return user;
    }

    private UserDTO makeUser(String id) {
        System.out.printf("비밀번호: ");
        String pw = sc.nextLine();
        System.out.printf("이름: ");
        String name = sc.nextLine();
        System.out.printf("전화번호: ");
        String phone = sc.nextLine();
        System.out.printf("주소: ");
        String address = sc.nextLine();
//        System.out.printf("계좌번호: ");
//        String account = sc.nextLine();

        if(pw.equals("0")) pw = null;
        if(name.equals("0")) name = null;
        if(phone.equals("0")) phone = null;
        if(address.equals("0")) address = null;
//        if(account.equals("0")) account = null;

        UserDTO user = UserDTO.builder()
                .user_id(id)
                .user_pw(pw)
                .user_name(name)
                .user_phone(phone)
                .user_address(address)
                .build();

        return user;
    }
}
