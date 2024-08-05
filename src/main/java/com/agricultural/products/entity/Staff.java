package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 员工信息
 *
 */
@Data
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编号
     */
    private Integer id;

    /**
     * 部门
     */
    private Integer deptId;

    /**
     * 姓名
     */
    private String fullName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 职位
     */
    private String position;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 入职时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "GMT+08")
    private Date entryDate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date createTime;

    /**
     * 企业id
     */
    private Integer eid;
}
