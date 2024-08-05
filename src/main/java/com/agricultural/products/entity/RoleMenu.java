package com.agricultural.products.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色菜单关系表
 */
@Data
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 菜单id
     */
    private Integer menuId;

}
