package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 供应商信息
 *
 */
@Data
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商编号
     */
    private Integer id;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 供应商地址
     */
    private String address;

    /**
     * 供应商联系人
     */
    private String contacts;

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
