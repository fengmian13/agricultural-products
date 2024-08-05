package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Activity;
import com.agricultural.products.entity.dto.ActivityDTO;
import com.agricultural.products.service.IActivityService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 活动信息相关接口类
 *
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private IActivityService activityService;


    /**
     * 保存活动信息
     * @param activity
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "活动信息")
    public Result save(@RequestBody Activity activity) {
        return Result.success(activityService.saveOrUpdate(activity));
    }

    /**
    * 删除单条活动信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "活动信息")
    public Result delete(@PathVariable Integer id) {
        activityService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除活动信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "活动信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        activityService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表活动信息
     * @param activity
    * @return
    */
    @GetMapping
    public Result findList(Activity activity) {
        return Result.success(activityService.list(activity));
    }

    /**
    * 根据id获取活动信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(activityService.getById(id));
    }

    /**
     * 活动信息分页数据
     * @param activityDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "活动信息")
    public Result findPage(ActivityDTO activityDTO) {
        return Result.success(new PageResult<>(activityService.page(activityDTO)));
    }

}

