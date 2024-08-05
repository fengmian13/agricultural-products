package com.agricultural.products.entity.dto;

import com.agricultural.products.entity.Staff;
import lombok.Data;

/**
 *
 * 员工信息数据传输对象
 *
 */
@Data
public class StaffDTO extends Staff {

    private static final long serialVersionUID = 1L;
    /**
     * 分页数量
     */
    private Integer pageSize;
    /**
     * 分页页数
     */
    private Integer pageNum;

    /**
     * 用户名称
     */
    private String createUserName;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 企业编号
     */
    private Integer eid;

}
