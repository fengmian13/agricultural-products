package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 企业信息
 *
 */
@Data
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业编号
     */
    private Integer id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 注册时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "GMT+08")
    private Date regDate;

    /**
     * 企业地址
     */
    private String address;

    /**
     * 管理人
     */
    private String manager;

    /**
     * 关联的供货商
     */
    private String supplierId;

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
