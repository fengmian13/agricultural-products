package com.agricultural.products.common;

/**
 * 常量类，用于存储项目中常用的状态码和其他固定值。
 */
public interface Constants {

    /**
     * HTTP状态码 200，表示请求成功。
     */
    String CODE_200 = "200";

    /**
     * HTTP状态码 401，表示未授权或权限不足。
     */
    String CODE_401 = "401";

    /**
     * HTTP状态码 400，表示客户端请求错误，如参数错误。
     */
    String CODE_400 = "400";

    /**
     * HTTP状态码 500，表示服务器内部错误。
     */
    String CODE_500 = "500";

    /**
     * 自定义业务状态码 600，表示其他业务异常。
     */
    String CODE_600 = "600";

    /**
     * 字典类型，用于表示图标（icon）的字典类型。
     */
    String DICT_TYPE_ICON = "icon";

    // 注意：通常情况下，接口中只定义常量，不定义方法。
    // 这里没有定义方法，符合Java接口的定义规范。
}