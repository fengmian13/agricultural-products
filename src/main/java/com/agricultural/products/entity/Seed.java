package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 种子信息
 *
 */
@Data
public class Seed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 种子编号
     */
    private Integer id;

    /**
     * 来源企业
     */
    private Integer eid;

    /**
     * 种子种类
     */
    private String seedType;

    /**
     * 种子数量
     */
    private Integer seedNum;

    /**
     * 种子成熟时间
     */
    private Integer days;

    /**
     * 单价
     */
    private String price;

    /**
     * 登记人
     */
    private String createUser;

    /**
     * 登记时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date createTime;


}
