package com.shinhan.controller;

import com.shinhan.common.OrderInterface;
import com.shinhan.dto.CartDTO;
import com.shinhan.dto.ProductDTO;
import com.shinhan.dto.UserDTO;
import com.shinhan.service.CartService;
import com.shinhan.service.ProductService;
import com.shinhan.view.CartView;
import com.shinhan.view.ProductView;

import java.util.List;
import java.util.Scanner;

public class CartController implements OrderInterface {
    Scanner sc = new Scanner(System.in);
    CartService cartService = new CartService();
    ProductService productService = new ProductService();
    CartView cartView = new CartView();
    ProductView productView = new ProductView();

    @Override
    public void execute(Object obj) {
        UserDTO user = (UserDTO) obj;
        boolean isStop = false;

        while(!isStop) {
            cartView.cartDisplay();
            int job = sc.nextInt();
            sc.nextLine();

            switch(job) {
                case 1 -> {
                    // 장바구니 조회
                    f_selectAll(user);
                }
                case 2 -> {
                    // 장바구니 담기
                    f_insertCart(user);
                }
                case 3 -> {
                    // 수량 변경
                    f_updateCart(user);
                }
                case 4 -> {
                    // 상품 구매
                    f_deleteCart(user);
                }
                case 5 -> {
                    // 이전 페이지
                    isStop = true;
                }
                case 6 -> {
                    // 프로그램 종료
                    System.exit(0);
                }
            }
        }
    }

    private void f_deleteCart(UserDTO user) {
        List<CartDTO> cartList = cartService.selectAll(user.getUser_id());
        if(cartList.isEmpty()) {
            cartView.displayCart(cartList);
            return;
        }

        cartService.deleteById(user.getUser_id());
        cartView.display("============== 상품 구매 완료 =============");
    }

    private void f_updateCart(UserDTO user) {
        System.out.printf("수량을 변경할 상품 ID 입력> ");
        int productId = sc.nextInt();
        sc.nextLine();

        CartDTO cart = cartService.selectById(productId);
        if(cart == null) {
            cartView.displayCart(cart);
            return;
        }

        System.out.printf("변경할 수량 입력> ");
        int num = sc.nextInt();
        sc.nextLine();

        cartService.updateByProduct(makeCart(productId, num, user));
        cartView.display("============= 수량 변경 완료 =============");
    }

    private void f_insertCart(UserDTO user) {
        System.out.printf("장바구니에 담을 상품 ID 입력> ");
        int productId = sc.nextInt();
        sc.nextLine();

        ProductDTO product = productService.selectById(productId);
        if(product == null) {
            productView.displayProduct(product);
            return;
        }

        System.out.printf("장바구니에 담을 수량 입력> ");
        int num = sc.nextInt();
        sc.nextLine();

        cartService.insertCart(makeCart(productId, num, user));
        cartView.display("============== 장바구니 담기 완료 =============");
    }

    private CartDTO makeCart(int productId, int num, UserDTO user) {
        CartDTO cart = CartDTO.builder()
                .product_id(productId)
                .cart_num(num)
                .user_id(user.getUser_id())
                .build();

        return cart;
    }

    private void f_selectAll(UserDTO user) {
        List<CartDTO> cartList = cartService.selectAll(user.getUser_id());
        cartView.displayCart(cartList);
    }
}
