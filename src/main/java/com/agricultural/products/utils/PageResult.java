package com.agricultural.products.utils;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {// 定义一个名为PageResult的泛型类，T是泛型参数，表示查询结果的类型
    // 定义一个long类型的变量total，用于存储查询结果的总数
    private long total;

    // 定义一个List类型的变量records，用于存储查询结果列表
    // 由于使用了泛型T，这个列表可以存储任何类型的对象，但通常是查询到的实体对象列表
    private List<T> records;

    // 构造方法，接受一个PageInfo<T>类型的参数pageInfo
    // 这个构造方法用于将PageInfo对象中的信息转换到PageResult对象中
    public PageResult(PageInfo<T> pageInfo) {
        // 从pageInfo中获取查询结果的总数，并赋值给this.total
        this.total = pageInfo.getTotal();

        // 从pageInfo中获取查询结果的列表，并赋值给this.records
        this.records = pageInfo.getList();
    }
}
