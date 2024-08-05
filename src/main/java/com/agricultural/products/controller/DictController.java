package com.agricultural.products.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Dict;
import com.agricultural.products.service.IDictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 接口控制器
 *
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Resource
    private IDictService dictService;

    @GetMapping("/types")
    public Result types(String types) {
        List<String> list = CharSequenceUtil.split(types, ",");
        List<Dict> dicts = dictService.list();
        dicts = dicts.stream().filter(d -> list.contains(d.getType())).collect(Collectors.toList());
        return Result.success(dicts);
    }

    @GetMapping("/type")
    public Result type(String type) {
        Dict dict = new Dict();
        dict.setType(type);
        return Result.success(dictService.list(dict));
    }

    @GetMapping("/label")
    public Result label(String type, String value) {
        Dict dict = new Dict();
        dict.setType(type);
        dict.setValue(value);
        List<Dict> list = dictService.list(dict);
        if (CollUtil.isNotEmpty(list)){
            return Result.success(list.get(0).getName());
        }
        return Result.success(value);
    }

    /**
     * 保存
     * @param dict
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Dict dict) {
        dictService.saveOrUpdate(dict);
        return Result.success();
    }

    /**
    * 删除单条
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        dictService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        dictService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询所有
    * @return
    */
    @GetMapping
    public Result findAll() {
        return Result.success(dictService.list());
    }

    /**
    * 根据id获取
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(dictService.getById(id));
    }


}

