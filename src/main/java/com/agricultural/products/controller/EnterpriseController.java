package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Enterprise;
import com.agricultural.products.entity.dto.EnterpriseDTO;
import com.agricultural.products.service.IEnterpriseService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 企业信息相关接口类
 *
 */
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Resource
    private IEnterpriseService enterpriseService;


    /**
     * 保存企业信息
     * @param enterprise
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "企业信息")
    public Result save(@RequestBody Enterprise enterprise) {
        return Result.success(enterpriseService.saveOrUpdate(enterprise));
    }

    /**
    * 删除单条企业信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "企业信息")
    public Result delete(@PathVariable Integer id) {
        enterpriseService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除企业信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "企业信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        enterpriseService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表企业信息
     * @param enterprise
    * @return
    */
    @GetMapping
    public Result findList(Enterprise enterprise) {
        return Result.success(enterpriseService.list(enterprise));
    }

    /**
    * 根据id获取企业信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(enterpriseService.getById(id));
    }

    /**
     * 企业信息分页数据
     * @param enterpriseDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "企业信息")
    public Result findPage(EnterpriseDTO enterpriseDTO) {
        return Result.success(new PageResult<>(enterpriseService.page(enterpriseDTO)));
    }

}

