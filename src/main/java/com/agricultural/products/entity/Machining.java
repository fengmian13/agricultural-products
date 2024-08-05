package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 加工信息
 *
 */
@Data
public class Machining implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 加工编号
     */
    private Integer id;

    /**
     * 农产品编号
     */
    private Integer pid;

    /**
     * 加工企业
     */
    private Integer eid;

    /**
     * 加工时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date machiningTime;

    /**
     * 加工员工
     */
    private String machiningStaff;

    /**
     * 加工图片
     */
    private String img;

    /**
     * 加工方式
     */
    private String method;

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
