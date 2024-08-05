package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Supplier;
import com.agricultural.products.entity.dto.SupplierDTO;
import com.agricultural.products.service.ISupplierService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 供应商信息相关接口类
 *
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Resource
    private ISupplierService supplierService;


    /**
     * 保存供应商信息
     * @param supplier
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "供应商信息")
    public Result save(@RequestBody Supplier supplier) {
        return Result.success(supplierService.saveOrUpdate(supplier));
    }

    /**
    * 删除单条供应商信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "供应商信息")
    public Result delete(@PathVariable Integer id) {
        supplierService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除供应商信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "供应商信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        supplierService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表供应商信息
     * @param supplier
    * @return
    */
    @GetMapping
    public Result findList(Supplier supplier) {
        return Result.success(supplierService.list(supplier));
    }

    /**
    * 根据id获取供应商信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(supplierService.getById(id));
    }

    /**
     * 供应商信息分页数据
     * @param supplierDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "供应商信息")
    public Result findPage(SupplierDTO supplierDTO) {
        return Result.success(new PageResult<>(supplierService.page(supplierDTO)));
    }

}

