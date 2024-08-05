package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Dept;
import com.agricultural.products.entity.dto.DeptDTO;
import com.agricultural.products.service.IDeptService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 部门信息相关接口类
 *
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private IDeptService deptService;


    /**
     * 保存部门信息
     * @param dept
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "部门信息")
    public Result save(@RequestBody Dept dept) {
        return Result.success(deptService.saveOrUpdate(dept));
    }

    /**
    * 删除单条部门信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "部门信息")
    public Result delete(@PathVariable Integer id) {
        deptService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除部门信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "部门信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        deptService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表部门信息
     * @param dept
    * @return
    */
    @GetMapping
    public Result findList(Dept dept) {
        return Result.success(deptService.list(dept));
    }

    /**
    * 根据id获取部门信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(deptService.getById(id));
    }

    /**
     * 部门信息分页数据
     * @param deptDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "部门信息")
    public Result findPage(DeptDTO deptDTO) {
        return Result.success(new PageResult<>(deptService.page(deptDTO)));
    }

}

