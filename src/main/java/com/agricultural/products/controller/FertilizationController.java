package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Fertilization;
import com.agricultural.products.entity.dto.FertilizationDTO;
import com.agricultural.products.service.IFertilizationService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 施肥信息相关接口类
 *
 */
@RestController
@RequestMapping("/fertilization")
public class FertilizationController {

    @Resource
    private IFertilizationService fertilizationService;


    /**
     * 保存施肥信息
     * @param fertilization
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "施肥信息")
    public Result save(@RequestBody Fertilization fertilization) {
        return Result.success(fertilizationService.saveOrUpdate(fertilization));
    }

    /**
    * 删除单条施肥信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "施肥信息")
    public Result delete(@PathVariable Integer id) {
        fertilizationService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除施肥信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "施肥信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        fertilizationService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表施肥信息
     * @param fertilization
    * @return
    */
    @GetMapping
    public Result findList(Fertilization fertilization) {
        return Result.success(fertilizationService.list(fertilization));
    }

    /**
    * 根据id获取施肥信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(fertilizationService.getById(id));
    }

    /**
     * 施肥信息分页数据
     * @param fertilizationDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "施肥信息")
    public Result findPage(FertilizationDTO fertilizationDTO) {
        return Result.success(new PageResult<>(fertilizationService.page(fertilizationDTO)));
    }

}

