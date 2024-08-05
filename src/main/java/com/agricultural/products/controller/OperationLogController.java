package com.agricultural.products.controller;

import com.agricultural.products.common.Result;
import com.agricultural.products.entity.OperationLog;
import com.agricultural.products.entity.dto.OperationLogDTO;
import com.agricultural.products.service.IOperationLogService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 操作日志信息相关接口类
 *
 */
@RestController
@RequestMapping("/operationLog")
public class OperationLogController {

    @Resource
    private IOperationLogService operationLogService;


    /**
     * 保存操作日志信息
     * @param operationLog
     * @return
     */
    @PostMapping
    public Result save(@RequestBody OperationLog operationLog) {
        return Result.success(operationLogService.saveOrUpdate(operationLog));
    }

    /**
    * 删除单条操作日志信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        operationLogService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除操作日志信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        operationLogService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表操作日志信息
     * @param operationLog
    * @return
    */
    @GetMapping
    public Result findList(OperationLog operationLog) {
        return Result.success(operationLogService.list(operationLog));
    }

    /**
    * 根据id获取操作日志信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(operationLogService.getById(id));
    }

    /**
     * 操作日志信息分页数据
     * @param operationLogDTO
     * @return
     */
    @GetMapping("/page")
    public Result findPage(OperationLogDTO operationLogDTO) {
        return Result.success(new PageResult<>(operationLogService.page(operationLogDTO)));
    }

}

