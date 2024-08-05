package com.agricultural.products.entity.dto;

import com.agricultural.products.entity.Enterprise;
import lombok.Data;

/**
 *
 * 企业信息数据传输对象
 *
 */
@Data
public class EnterpriseDTO extends Enterprise {

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

}
