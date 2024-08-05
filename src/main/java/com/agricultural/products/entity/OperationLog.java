package com.agricultural.products.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * 操作日志信息
 *
 */
@Data
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志编号
     */
    private Integer id;

    /**
     * 操作行为
     */
    private String method;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 操作人
     */
    private String createUser;

    /**
     * 操作时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08")
    private Date createTime;


}
