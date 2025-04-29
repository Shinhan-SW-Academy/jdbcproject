package com.shinhan.controller;

import com.shinhan.common.OrderInterface;
import com.shinhan.dto.BusinessDTO;
import com.shinhan.dto.ProductDTO;
import com.shinhan.dto.UserDTO;
import com.shinhan.service.ProductService;
import com.shinhan.view.ProductView;

import java.util.List;
import java.util.Scanner;

public class ProductController implements OrderInterface {
    Scanner sc = new Scanner(System.in);
    ProductService productService = new ProductService();
    ProductView productView = new ProductView();

    @Override
    public void execute(Object obj) {
        boolean isStop = false;
        int job = 0;

        while(!isStop) {
            if(obj instanceof UserDTO user) {
                productView.uProductDisplay();
                job = sc.nextInt();
                sc.nextLine();

                switch(job) {
                    case 1 -> {
                        // 전체 상품 조회
                        f_selectAll();
                    }
                    case 2 -> {
                        // 상품 검색
                        f_selectByName();
                    }
                    case 3 -> {
                        // 이전 페이지
                        isStop = true;
                    }
                    case 4 -> {
                        // 프로그램 종료
                        System.exit(0);
                    }
                }

            } else if (obj instanceof BusinessDTO business) {
                productView.bProductDisplay();
                job = sc.nextInt();
                sc.nextLine();

                switch(job) {
                    case 1 -> {
                        // 상품 추가
                        f_insertProduct(business.getBusiness_id());
                    }
                    case 2 -> {
                        // 상품 수정
                        f_updateById();
                    }
                    case 3 -> {
                        // 상품 삭제
                        f_deleteById();
                    }
                    case 4 -> {
                        // 내 상품 조회
                        f_selectById(business.getBusiness_id());
                    }
                    case 5 -> {
                        isStop = true;
                    }
                    case 6 -> {
                        System.exit(0);
                    }

                }
            }
        }
    }

    private void f_selectById(String businessId) {
        List<ProductDTO> productList = productService.selectById(businessId);
        productView.displayProduct(productList);
    }

    private void f_deleteById() {
        System.out.printf("삭제할 상품 ID 입력> ");
        int productId = sc.nextInt();
        sc.nextLine();

        ProductDTO existProduct = productService.selectById(productId);
        if(existProduct == null) {
            productView.displayProduct(existProduct);
            return;
        }

        productService.deleteById(productId);
        productView.display("============= 상품 삭제 완료 =============");
    }

    private void f_updateById() {
        System.out.printf("수정할 상품 ID 입력> ");
        int productId = sc.nextInt();
        sc.nextLine();

        ProductDTO existProduct = productService.selectById(productId);
        if(existProduct == null) {
            productView.displayProduct(existProduct);
            return;
        }

        ProductDTO product = productService.updateById(makeProduct(productId, existProduct.getBusiness_id()));
        productView.display("============= 상품 수정 완료 =============");
        productView.displayProduct(product);
    }

    private void f_insertProduct(String businessId) {
        ProductDTO product = productService.insertProduct(makeProduct(null, businessId));
        productView.display("============= 상품 등록 완료 =============");
        productView.displayProduct(product);
    }

    private void f_selectByName() {
        System.out.printf("상품명 입력> ");
        List<ProductDTO> productList = productService.selectByName(sc.nextLine());
        productView.displayProduct(productList);
    }

    private void f_selectAll() {
        List<ProductDTO> productList = productService.selectAll();
        productView.displayProduct(productList);
    }

    private ProductDTO makeProduct(Integer productId, String businessId) {
        System.out.printf("상품명 입력> ");
        String name = sc.nextLine();
        System.out.printf("상품 가격 입력> ");
        Integer price = sc.nextInt();
        sc.nextLine();
        System.out.printf("상품 정보 입력> ");
        String info = sc.nextLine();
        System.out.printf("상품 재고 입력> ");
        Integer inventory = sc.nextInt();
        sc.nextLine();

        if(name.equals("0")) name = null;
        if(price == 0) price = null;
        if(info.equals("0")) info = null;
        if(inventory == 0) inventory = null;

        ProductDTO product = ProductDTO.builder()
                .product_id(productId)
                .business_id(businessId)
                .product_name(name)
                .product_price(price)
                .product_info(info)
                .product_inventory(inventory)
                .build();

        return product;
    }
}
