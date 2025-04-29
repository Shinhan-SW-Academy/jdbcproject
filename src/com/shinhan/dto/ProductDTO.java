package com.shinhan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private String product_id;
    private String business_id;
    private String product_name;
    private Integer product_price;
    private String product_info;
    private Integer product_inventory;
}
