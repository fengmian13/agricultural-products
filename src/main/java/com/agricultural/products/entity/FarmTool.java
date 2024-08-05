package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 农具信息
 *
 */
@Data
public class FarmTool implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 农具编号
     */
    private Integer id;

    /**
     * 来源企业
     */
    private Integer eid;

    /**
     * 农具种类
     */
    private String toolType;

    /**
     * 农具数量
     */
    private Integer toolNum;

    /**
     * 农具图片
     */
    private String img;

    /**
     * 单价
     */
    private String price;

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
