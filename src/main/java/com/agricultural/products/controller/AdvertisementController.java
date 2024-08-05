package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Advertisement;
import com.agricultural.products.entity.dto.AdvertisementDTO;
import com.agricultural.products.service.IAdvertisementService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 广告信息相关接口类
 *
 */
@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Resource
    private IAdvertisementService advertisementService;


    /**
     * 保存广告信息
     * @param advertisement
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "广告信息")
    public Result save(@RequestBody Advertisement advertisement) {
        return Result.success(advertisementService.saveOrUpdate(advertisement));
    }

    /**
    * 删除单条广告信息
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "广告信息")
    public Result delete(@PathVariable Integer id) {
        advertisementService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除广告信息
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "广告信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        advertisementService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询列表广告信息
     * @param advertisement
    * @return
    */
    @GetMapping
    public Result findList(Advertisement advertisement) {
        return Result.success(advertisementService.list(advertisement));
    }

    /**
    * 根据id获取广告信息
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(advertisementService.getById(id));
    }

    /**
     * 广告信息分页数据
     * @param advertisementDTO
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "广告信息")
    public Result findPage(AdvertisementDTO advertisementDTO) {
        return Result.success(new PageResult<>(advertisementService.page(advertisementDTO)));
    }

}

