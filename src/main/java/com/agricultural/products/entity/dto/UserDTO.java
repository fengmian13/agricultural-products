package com.agricultural.products.entity.dto;

import com.agricultural.products.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * 接受前端登录请求的参数
 */
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private String sex;
    private String age;
    private String avatarUrl;
    private String token;
    private String role;
    private List<Menu> menus;
}
