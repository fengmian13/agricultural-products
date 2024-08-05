package com.agricultural.products.entity.dto;

import com.agricultural.products.entity.Seed;
import lombok.Data;

/**
 *
 * 种子信息数据传输对象
 *
 */
@Data
public class SeedDTO extends Seed {

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
     * 企业名称
     */
    private String enterpriseName;

}
