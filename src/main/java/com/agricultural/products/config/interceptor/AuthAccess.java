package com.agricultural.products.config.interceptor;

import java.lang.annotation.*;
//标记注解
// 定义一个名为 AuthAccess 的注解接口
// 使用 @interface 关键字声明一个注解
// 该注解将用于标记需要特定访问权限的方法
@Target(ElementType.METHOD)// 指定该注解只能用于方法上
@Retention(RetentionPolicy.RUNTIME)// 指定该注解的生命周期，这里为 RUNTIME，意味着该注解在运行期依然有效，可以通过反射获取
@Documented// 表明该注解会被 javadoc 和类似的工具记录
public @interface AuthAccess {
    // 这里没有定义任何成员变量，因此是一个标记注解（marker annotation）
    // 标记注解用于表明被其注解的元素具有某种“标记”，但并不包含其他信息
}
