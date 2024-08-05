package com.agricultural.products.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单信息
 */
@Data
public class Menu implements Serializable {

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
     * 路径
     */
    private String path;

    /**
     * 图标
     */
    private String icon;

    /**
     * 描述
     */
    private String description;

    /**
     * 父级id
     */
    private Integer pid;

    /**
     *  页面路径
     */
    private String pagePath;

    /**
     *  排序
     */
    private Integer sortNum;

    /**
     * 子菜单
     */
    private List<Menu> children;
}
