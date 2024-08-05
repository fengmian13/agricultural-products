package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 部门信息
 *
 */
@Data
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门编号
     */
    private Integer id;

    /**
     * 所属企业
     */
    private Integer eid;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门经理
     */
    private String manager;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date createTime;


}
