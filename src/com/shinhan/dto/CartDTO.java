package com.shinhan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {
    private Integer cart_id;
    private String user_id;
    private Integer product_id;
    private Integer cart_num;
    private Integer cart_price;
    private String product_name;
}
