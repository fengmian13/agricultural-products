package com.agricultural.products.common;

import lombok.AllArgsConstructor; // 提供全参构造函数
import lombok.Data; // 提供getter、setter、equals、hashCode和toString方法
import lombok.NoArgsConstructor; // 提供无参构造函数

/**
 * 接口统一返回包装类
 * 用于API接口返回的统一数据格式
 */
@Data // 使用Lombok的@Data注解，自动生成getter、setter、equals、hashCode和toString方法
@NoArgsConstructor // 使用Lombok的@NoArgsConstructor注解，自动生成无参构造函数
@AllArgsConstructor // 使用Lombok的@AllArgsConstructor注解，自动生成全参构造函数
public class Result {

    // 响应的状态码，用于标识请求的处理结果
    private String code;

    // 响应的消息，通常是对状态码的描述或者额外的错误信息
    private String msg;

    // 响应的数据，可以是任何Java对象，用于传递业务数据
    private Object data;

    /**
     * 创建一个成功的响应结果，状态码为200，消息为空，没有数据
     * @return 成功的Result对象
     */
    public static Result success() {
        return new Result(Constants.CODE_200, "", null); // 调用Constants接口中定义的CODE_200常量
    }

    /**
     * 创建一个成功的响应结果，状态码为200，消息为空，带有指定的数据
     * @param data 响应的数据
     * @return 成功的Result对象
     */
    public static Result success(Object data) {
        return new Result(Constants.CODE_200, "", data); // 调用Constants接口中定义的CODE_200常量
    }

    /**
     * 创建一个错误的响应结果，允许调用者自定义错误状态码和消息
     * @param code 错误的状态码
     * @param msg 错误的消息
     * @return 错误的Result对象
     */
    public static Result error(String code, String msg) {
        return new Result(code, msg, null); // 使用传入的code和msg创建Result对象
    }

    /**
     * 创建一个默认的错误响应结果，状态码为500，消息为"系统错误"，没有数据
     * @return 错误的Result对象
     */
    public static Result error() {
        return new Result(Constants.CODE_500, "系统错误", null); // 调用Constants接口中定义的CODE_500常量
    }

}