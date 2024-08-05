package com.agricultural.products.controller;

import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Result;
import com.agricultural.products.entity.Role;
import com.agricultural.products.service.IRoleService;
import com.agricultural.products.utils.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 接口控制器
 *
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;


    /**
     * 保存
     * @param role
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "角色信息")
    public Result save(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
        return Result.success();
    }

    /**
    * 删除单条
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "角色信息")
    public Result delete(@PathVariable Integer id) {
        roleService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "角色信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        roleService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询所有
    * @return
    */
    @GetMapping
    public Result findAll() {
        return Result.success(roleService.list());
    }

    /**
    * 根据id获取
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(roleService.getById(id));
    }

    /**
     * 分页数据
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "角色信息")
    public Result findPage(@RequestParam(required = false) String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        Role role = new Role();
        role.setName(name);
        return Result.success(new PageResult<>(roleService.page(pageNum, pageSize, role)));
    }

    /**
     * 绑定角色和菜单的关系
     * @param roleId 角色id
     * @param menuIds 菜单id数组
     * @return
     */
    @PostMapping("/roleMenu/{roleId}")
    public Result roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId, menuIds);
        return Result.success();
    }

    @GetMapping("/roleMenu/{roleId}")
    public Result getRoleMenu(@PathVariable Integer roleId) {
        return Result.success( roleService.getRoleMenu(roleId));
    }

}

