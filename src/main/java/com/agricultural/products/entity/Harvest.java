package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 收割信息
 *
 */
@Data
public class Harvest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收割记录编号
     */
    private Integer id;

    /**
     * 播种编号
     */
    private Integer sid;

    /**
     * 收割时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date harvestTime;

    /**
     * 收割员工
     */
    private String harvester;

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
