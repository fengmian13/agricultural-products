package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import com.agricultural.products.service.IMachiningService;
import com.agricultural.products.entity.Machining;
import com.agricultural.products.entity.dto.MachiningDTO;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * 加工信息相关接口类
 *
 */
@RestController
@RequestMapping("/machining")
public class MachiningController {

    @Resource
    private IMachiningService machiningService;


    /**
     * 保存加工信息
     * @param machining
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "加工信息")
    public Result save(@RequestBody Machining machining) {
        return Result.success(machiningService.saveOrUpdate(machining));
    }

    /**
    * 删除单条加工信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "加工信息")
    public Result delete(@PathVariable Integer id) {
        machiningService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除加工信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "加工信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        machiningService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表加工信息
     * @param machining
    * @return
    */
    @GetMapping
    public Result findList(Machining machining) {
        return Result.success(machiningService.list(machining));
    }

    /**
    * 根据id获取加工信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(machiningService.getById(id));
    }

    /**
     * 加工信息分页数据
     * @param machiningDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "加工信息")
    public Result findPage(MachiningDTO machiningDTO) {
        return Result.success(new PageResult<>(machiningService.page(machiningDTO)));
    }

}

