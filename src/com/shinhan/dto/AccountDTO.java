package com.shinhan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AccountDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserAccountDTO {
        private Integer account_id;
        private String user_id;
        private String account_name;
        private Integer balance;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BusinessAccountDTO {
        private Integer account_id;
        private String business_id;
        private String account_name;
        private Integer balance;
    }
}