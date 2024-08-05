package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Seed;
import com.agricultural.products.entity.dto.SeedDTO;
import com.agricultural.products.service.ISeedService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 种子信息相关接口类
 *
 */
@RestController
@RequestMapping("/seed")
public class SeedController {

    @Resource
    private ISeedService seedService;


    /**
     * 保存种子信息
     * @param seed
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "种子信息")
    public Result save(@RequestBody Seed seed) {
        return Result.success(seedService.saveOrUpdate(seed));
    }

    /**
    * 删除单条种子信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "种子信息")
    public Result delete(@PathVariable Integer id) {
        seedService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除种子信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "种子信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        seedService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表种子信息
     * @param seed
    * @return
    */
    @GetMapping
    public Result findList(Seed seed) {
        return Result.success(seedService.list(seed));
    }

    /**
    * 根据id获取种子信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(seedService.getById(id));
    }

    /**
     * 种子信息分页数据
     * @param seedDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "种子信息")
    public Result findPage(SeedDTO seedDTO) {
        return Result.success(new PageResult<>(seedService.page(seedDTO)));
    }

}

