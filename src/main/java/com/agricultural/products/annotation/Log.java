package com.agricultural.products.annotation;

import java.lang.annotation.*;

/**
 * 日志记录注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Log {
    /**
     * 操作名称
     *
     * @return
     */
    String method() default "";

    /**
     * 资源名称
     *
     * @return
     */
    String resource() default "";


    /*test*/
}
