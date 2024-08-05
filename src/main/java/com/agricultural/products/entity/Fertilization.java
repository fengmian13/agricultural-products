package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 施肥信息
 *
 */
@Data
public class Fertilization implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 施肥编号
     */
    private Integer id;

    /**
     * 播种编号
     */
    private Integer sid;

    /**
     * 施肥时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date fertilizationTime;

    /**
     * 施肥员工
     */
    private String fertilizationer;

    /**
     * 化肥种类
     */
    private String fertilizerType;

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
