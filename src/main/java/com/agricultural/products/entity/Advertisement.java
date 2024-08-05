package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 广告信息
 *
 */
@Data
public class Advertisement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 广告编号
     */
    private Integer id;

    /**
     * 商品
     */
    private String goods;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片
     */
    private String img;

    /**
     * 发布人
     */
    private String createUser;

    /**
     * 发布时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date createTime;


}
