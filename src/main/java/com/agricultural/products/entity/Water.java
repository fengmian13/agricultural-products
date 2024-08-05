package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 浇水信息
 *
 */
@Data
public class Water implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 浇水编号
     */
    private Integer id;

    /**
     * 播种编号
     */
    private Integer sid;

    /**
     * 浇水时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date waterTime;

    /**
     * 浇水员工
     */
    private String waterer;

    /**
     * 浇水量
     */
    private String waterAmount;

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
