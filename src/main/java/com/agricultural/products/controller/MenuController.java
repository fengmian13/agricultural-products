package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Constants;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Dict;
import com.agricultural.products.entity.Menu;
import com.agricultural.products.mapper.DictMapper;
import com.agricultural.products.service.IMenuService;
import com.agricultural.products.utils.PageResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 系统菜单接口控制器
 *
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @Resource
    private DictMapper dictMapper;
    /**
     * 保存
     * @param menu
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "菜单信息")
    public Result save(@RequestBody Menu menu) {
        menuService.saveOrUpdate(menu);
        return Result.success();
    }

    /**
    * 删除单条
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "菜单信息")
    public Result delete(@PathVariable Integer id) {
        menuService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "菜单信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        menuService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping("/ids")
    public Result findAllIds() {
        return Result.success(menuService.list().stream().map(Menu::getId));
    }

    /**
    * 查询所有
    * @return
    */
    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "") String name) {
        return Result.success(menuService.findMenus(name));
    }

    /**
    * 根据id获取
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.getById(id));
    }

    /**
     * 分页数据
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "菜单信息")
    public Result findPage(@RequestParam(required = false) String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        Menu menu = new Menu();
        menu.setName(name);
        PageInfo<Menu> page = menuService.page(pageNum, pageSize, menu);
        return Result.success(new PageResult<>(page));
    }

    @GetMapping("/icons")
    public Result getIcons() {
        Dict dict = new Dict();
        dict.setType(Constants.DICT_TYPE_ICON);
        return Result.success(dictMapper.selectList(dict));
    }

}

