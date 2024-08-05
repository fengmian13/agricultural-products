package com.agricultural.products.common;

/**
 * 角色枚举类
 * 定义系统中使用的角色
 */
public enum RoleEnum {

    /**
     * 管理员角色
     * 系统中拥有最高权限的角色
     */
    ROLE_ADMIN,

    /**
     * 普通用户角色
     * 系统中权限相对较低的角色
     */
    ROLE_USER;

    // 注意：枚举类型默认包含了toString()方法，返回的是枚举常量的名称（例如："ROLE_ADMIN"）
    // 如果需要更复杂的字符串表示，可以自定义toString()方法

    // 通常还会在枚举中定义一些辅助方法，比如根据角色名称获取枚举常量等
    // 但在这个例子中，为了简洁起见，我们没有添加这些方法
}