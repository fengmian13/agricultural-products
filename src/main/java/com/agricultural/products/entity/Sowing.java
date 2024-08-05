package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 播种信息
 *
 */
@Data
public class Sowing implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 播种编号
     */
    private Integer id;

    /**
     * 来源种子
     */
    private Integer sid;

    /**
     * 生产场地
     */
    private String area;

    /**
     * 播种时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date sowingTime;

    /**
     * 播种员工
     */
    private String seeder;

    /**
     * 成长状态
     */
    private String status;

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
