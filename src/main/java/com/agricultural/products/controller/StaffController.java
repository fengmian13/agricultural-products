package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Staff;
import com.agricultural.products.entity.dto.StaffDTO;
import com.agricultural.products.service.IStaffService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 员工信息相关接口类
 *
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Resource
    private IStaffService staffService;


    /**
     * 保存员工信息
     * @param staff
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "员工信息")
    public Result save(@RequestBody Staff staff) {
        return Result.success(staffService.saveOrUpdate(staff));
    }

    /**
    * 删除单条员工信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "员工信息")
    public Result delete(@PathVariable Integer id) {
        staffService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除员工信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "员工信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        staffService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表员工信息
     * @param staff
    * @return
    */
    @GetMapping
    public Result findList(Staff staff) {
        return Result.success(staffService.list(staff));
    }

    /**
    * 根据id获取员工信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(staffService.getById(id));
    }

    /**
     * 员工信息分页数据
     * @param staffDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "员工信息")
    public Result findPage(StaffDTO staffDTO) {
        return Result.success(new PageResult<>(staffService.page(staffDTO)));
    }

}

