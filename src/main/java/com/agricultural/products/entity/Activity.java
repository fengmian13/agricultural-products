package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 活动信息
 *
 */
@Data
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动编号
     */
    private Integer id;

    /**
     * 活动时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date activityTime;

    /**
     * 活动内容
     */
    private String content;

    /**
     * 活动地址
     */
    private String address;

    /**
     * 活动方式
     */
    private String mode;

    /**
     * 活动发布人
     */
    private String createUser;

    /**
     * 活动发布时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date createTime;


}
