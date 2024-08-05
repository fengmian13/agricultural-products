package com.agricultural.products.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * 角色信息
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 唯一标识
     */
    private String flag;


}
