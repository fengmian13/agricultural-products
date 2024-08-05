package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Harvest;
import com.agricultural.products.entity.dto.HarvestDTO;
import com.agricultural.products.service.IHarvestService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 收割信息相关接口类
 *
 */
@RestController
@RequestMapping("/harvest")
public class HarvestController {

    @Resource
    private IHarvestService harvestService;


    /**
     * 保存收割信息
     * @param harvest
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "收割信息")
    public Result save(@RequestBody Harvest harvest) {
        return Result.success(harvestService.saveOrUpdate(harvest));
    }

    /**
    * 删除单条收割信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "收割信息")
    public Result delete(@PathVariable Integer id) {
        harvestService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除收割信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "收割信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        harvestService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表收割信息
     * @param harvest
    * @return
    */
    @GetMapping
    public Result findList(Harvest harvest) {
        return Result.success(harvestService.list(harvest));
    }

    /**
    * 根据id获取收割信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(harvestService.getById(id));
    }

    /**
     * 收割信息分页数据
     * @param harvestDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "收割信息")
    public Result findPage(HarvestDTO harvestDTO) {
        return Result.success(new PageResult<>(harvestService.page(harvestDTO)));
    }

}

