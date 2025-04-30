package com.shinhan.view;

import com.shinhan.dto.ProductDTO;

import java.util.List;

public class ProductView {
    public void display(String message) {
        System.out.println(message);
    }

    public void uProductDisplay() {
        System.out.println("-----------------------------");
        System.out.println("1. 전체 상품 조회 | 2. 상품 상세 조회 | 3. 상품 검색");
        System.out.println("4. 매장 검색 | 5. 이전 페이지 | 6. 종료");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }

    public void bProductDisplay() {
        System.out.println("-----------------------------");
        System.out.println("1. 상품 추가 | 2. 상품 수정 | 3. 상품 삭제");
        System.out.println("4. 내 상품 조회 | 5. 이전 페이지 | 6. 종료");
        System.out.println("-----------------------------");
        System.out.printf("작업 선택> ");
    }

    public void displayProduct(List<ProductDTO> productList) {
        if(productList.isEmpty()) {
            display("조회된 상품이 없습니다.");
            return;
        }

        System.out.println("============= 모든 상품 조회 =============");
        productList.forEach(product ->
                System.out.printf("상품 코드 <%d> 상품명 [%s] %d원\n", product.getProduct_id(),product.getProduct_name(), product.getProduct_price()));
    }

    public void displayProduct(ProductDTO product) {
        if(product == null) {
            display("해당 상품이 없습니다.");
            return;
        }

        System.out.printf("상품 코드 <%d>\n상품명 [%s] %d원\n|%s|\n", product.getProduct_id(),
                product.getProduct_name(), product.getProduct_price(), product.getProduct_info());
    }
}
