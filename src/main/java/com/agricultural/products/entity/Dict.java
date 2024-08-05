package com.agricultural.products.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典信息
 */
@Data
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 内容
     */
    private String value;

    /**
     * 类型
     */
    private String type;


}
