package com.shinhan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_phone;
    private String user_address;
}
