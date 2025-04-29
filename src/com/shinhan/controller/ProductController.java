package com.shinhan.controller;

import com.shinhan.common.OrderInterface;
import com.shinhan.dto.BusinessDTO;
import com.shinhan.dto.ProductDTO;
import com.shinhan.dto.UserDTO;
import com.shinhan.service.ProductService;
import com.shinhan.view.UserView;

import java.util.Scanner;

public class ProductController implements OrderInterface {
    Scanner sc = new Scanner(System.in);
    ProductService productService = new ProductService();
    UserView userView = new UserView();

    @Override
    public void execute(Object user) {
        boolean isStop = false;
        OrderInterface controller = null;

        while(!isStop) {
            int job = sc.nextInt();
            sc.nextLine();

            if(user instanceof UserDTO) {
                switch(job) {
                    case 1 -> {
                        // 전체 상품 조회
                    }
                    case 2 -> {
                        // 상품 검색
                    }
                    case 3 -> {
                        // 이전 페이지
                    }
                    case 4 -> {
                        // 종료
                    }
                }

            } else if (user instanceof BusinessDTO) {
                switch(job) {
                    case 1 -> {
                        // 상품 추가
                    }
                    case 2 -> {
                        // 상품 수정
                    }
                    case 3 -> {
                        // 상품 삭제
                    }
                    case 4 -> {
                        // 내 상품 조회
                    }
                    case 5 -> {
                        // 이전 페이지
                    }
                    case 6 -> {
                        // 종료
                    }

                }
            }
        }
    }

    private ProductDTO makeProduct(String id) {

    }
}
