package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.FarmTool;
import com.agricultural.products.entity.dto.FarmToolDTO;
import com.agricultural.products.service.IFarmToolService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 农具信息相关接口类
 *
 */
@RestController
@RequestMapping("/farmTool")
public class FarmToolController {

    @Resource
    private IFarmToolService farmToolService;


    /**
     * 保存农具信息
     * @param farmTool
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "农具信息")
    public Result save(@RequestBody FarmTool farmTool) {
        return Result.success(farmToolService.saveOrUpdate(farmTool));
    }

    /**
    * 删除单条农具信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "农具信息")
    public Result delete(@PathVariable Integer id) {
        farmToolService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除农具信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "农具信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        farmToolService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表农具信息
     * @param farmTool
    * @return
    */
    @GetMapping
    public Result findList(FarmTool farmTool) {
        return Result.success(farmToolService.list(farmTool));
    }

    /**
    * 根据id获取农具信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(farmToolService.getById(id));
    }

    /**
     * 农具信息分页数据
     * @param farmToolDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "农具信息")
    public Result findPage(FarmToolDTO farmToolDTO) {
        return Result.success(new PageResult<>(farmToolService.page(farmToolDTO)));
    }

}

