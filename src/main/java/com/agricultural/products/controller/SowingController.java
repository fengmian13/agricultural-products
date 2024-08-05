package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Sowing;
import com.agricultural.products.entity.dto.SowingDTO;
import com.agricultural.products.service.ISowingService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 播种信息相关接口类
 *
 */
@RestController
@RequestMapping("/sowing")
public class SowingController {

    @Resource
    private ISowingService sowingService;


    /**
     * 保存播种信息
     * @param sowing
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "播种信息")
    public Result save(@RequestBody Sowing sowing) {
        return Result.success(sowingService.saveOrUpdate(sowing));
    }

    /**
    * 删除单条播种信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "播种信息")
    public Result delete(@PathVariable Integer id) {
        sowingService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除播种信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "播种信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        sowingService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表播种信息
     * @param sowing
    * @return
    */
    @GetMapping
    public Result findList(Sowing sowing) {
        return Result.success(sowingService.list(sowing));
    }

    /**
    * 根据id获取播种信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(sowingService.getById(id));
    }

    /**
     * 播种信息分页数据
     * @param sowingDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "播种信息")
    public Result findPage(SowingDTO sowingDTO) {
        return Result.success(new PageResult<>(sowingService.page(sowingDTO)));
    }

}

