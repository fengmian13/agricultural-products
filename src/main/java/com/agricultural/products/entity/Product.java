package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 农产品信息
 *
 */
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 农产品编号
     */
    private Integer id;

    /**
     * 来源播种编号
     */
    private Integer sid;

    /**
     * 农产品名称
     */
    private String name;

    /**
     * 生产场地
     */
    private String area;

    /**
     * 保质期
     */
    private String shelfLife;

    /**
     * 播种时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date sowingTime;

    /**
     * 施肥次数
     */
    private Integer fertilizationNum;

    /**
     * 浇水次数
     */
    private Integer waterNum;

    /**
     * 收割时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date harvestTime;

    /**
     * 登记人
     */
    private String createUser;

    /**
     * 登记时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date createTime;

    /**
     * 图片
     */
    private String img;
}
