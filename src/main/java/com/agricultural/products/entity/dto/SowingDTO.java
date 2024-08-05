package com.agricultural.products.entity.dto;

import com.agricultural.products.entity.Sowing;
import lombok.Data;

/**
 *
 * 播种信息数据传输对象
 *
 */
@Data
public class SowingDTO extends Sowing {

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
     * 作物种子
     */
    private String seedType;

}
