package com.agricultural.products.entity.dto;

import lombok.Data;

@Data
public class UserPasswordDTO {
    private String username;
    private String phone;
    private String password;
    private String newPassword;
}
