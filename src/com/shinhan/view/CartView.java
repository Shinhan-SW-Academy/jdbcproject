package com.shinhan.view;

import com.shinhan.dto.CartDTO;

import java.util.List;

public class CartView {
    public void display(String message) {
        System.out.println(message);
    }

    public void cartDisplay() {
        System.out.println("-----------------------------");
        System.out.println("1. 장바구니 조회 | 2. 장바구니 담기 | 3. 수량 변경");
        System.out.println("4. 상품 구매 | 5. 이전 페이지 | 6. 종료");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }

    public void displayCart(List<CartDTO> cartList) {
        if(cartList.isEmpty()) {
            display("장바구니에 담긴 상품이 없습니다.");
            return;
        }

        System.out.println("============= 장바구니 상품 조회 =============");
        cartList.forEach(cart -> System.out.printf("선택 <%d> 상품 [%s, %d건, %d원]\n",
                cart.getCart_id(), cart.getProduct_id(), cart.getCart_num(), cart.getCart_price()));
    }

    public void displayCart(CartDTO cart) {
        if(cart == null) {
            display("해당 상품이 장바구니에 없습니다.");
            return;
        }

        System.out.println(cart);
    }

    public void purchase() {
        System.out.println("-----------------------------");
        System.out.println("1. 전체 구매 | 2. 선택 구매 | 3. 이전 페이지");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }
}
