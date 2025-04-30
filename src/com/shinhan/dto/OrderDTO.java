package com.shinhan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Integer order_id;
    private String user_id;
    private String business_id;
    private Integer product_id;
    private Integer order_num;
    private Integer order_price;
    private OrderStatus order_status;
    private String product_name;
}
