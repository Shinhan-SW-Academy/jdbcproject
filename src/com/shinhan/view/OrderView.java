package com.shinhan.view;

import com.shinhan.dto.OrderDTO;

import java.util.List;

public class OrderView {
    public void display(String message) {
        System.out.println(message);
    }

    public void uOrderDisplay() {
        System.out.println("-----------------------------");
        System.out.println("1. 주문 조회 | 2. 주문 취소 | 3. 이전 페이지");
        System.out.println("4. 종료");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }

    public void bOrderDisplay() {
        System.out.println("-----------------------------");
        System.out.println("1. 주문 조회 | 2. 주문 환불 | 3. 이전 페이지");
        System.out.println("4. 종료");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }

    public void displayOrder(List<OrderDTO> orderList) {
        if(orderList.isEmpty()) {
            display("조회된 주문이 없습니다.");
            return;
        }

        System.out.println("============= 모든 상품 조회 =============");
        orderList.forEach(order ->
                System.out.printf("주문 번호 <%d> 주문 상품 [%s, %d건, %d원] 주문 상태 [%s]\n",
                        order.getOrder_id(), order.getProduct_name(), order.getOrder_num(), order.getOrder_price(), order.getOrder_status()));
    }

    public void displayRank(List<OrderDTO> rankList) {
        if(rankList.isEmpty()) {
            display("조회된 주문이 없습니다.");
            return;
        }

        System.out.println("============= 모든 상품 조회 =============");
        rankList.forEach(rank ->
                System.out.printf("상품 번호 <%s> 상품 [%s, 총 %d건, 총 %d원]\n",
                        rank.getProduct_id(), rank.getProduct_name(), rank.getOrder_num(), rank.getOrder_price()));
    }

    public void displayOrder(OrderDTO order) {
        if(order == null) {
            display("해당 주문이 없습니다.");
            return;
        }

        System.out.printf("주문 번호 <%d> 주문 상품 [%s, %d건, %d원] 주문 상태 [%s]\n",
                order.getOrder_id(), order.getProduct_name(), order.getOrder_num(), order.getOrder_price(), order.getOrder_status());
    }
}
