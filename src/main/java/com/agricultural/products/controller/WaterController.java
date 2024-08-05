package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Water;
import com.agricultural.products.entity.dto.WaterDTO;
import com.agricultural.products.service.IWaterService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 浇水信息相关接口类
 *
 */
@RestController
@RequestMapping("/water")
public class WaterController {

    @Resource
    private IWaterService waterService;


    /**
     * 保存浇水信息
     * @param water
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "浇水信息")
    public Result save(@RequestBody Water water) {
        return Result.success(waterService.saveOrUpdate(water));
    }

    /**
    * 删除单条浇水信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "浇水信息")
    public Result delete(@PathVariable Integer id) {
        waterService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除浇水信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "浇水信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        waterService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表浇水信息
     * @param water
    * @return
    */
    @GetMapping
    public Result findList(Water water) {
        return Result.success(waterService.list(water));
    }

    /**
    * 根据id获取浇水信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(waterService.getById(id));
    }

    /**
     * 浇水信息分页数据
     * @param waterDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "浇水信息")
    public Result findPage(WaterDTO waterDTO) {
        return Result.success(new PageResult<>(waterService.page(waterDTO)));
    }

}

