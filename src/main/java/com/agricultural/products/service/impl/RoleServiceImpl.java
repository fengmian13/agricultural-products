package com.agricultural.products.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.agricultural.products.service.IMenuService;
import com.agricultural.products.service.IRoleService;
import com.agricultural.products.entity.Menu;
import com.agricultural.products.entity.Role;
import com.agricultural.products.entity.RoleMenu;
import com.agricultural.products.mapper.RoleMapper;
import com.agricultural.products.mapper.RoleMenuMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 *
 * 
 * 服务实现类
 *
 */
@Service
public class RoleServiceImpl implements IRoleService {


    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        // 先删除当前角色id所有的绑定关系
        roleMenuMapper.deleteByRoleId(roleId);

        // 再把前端传过来的菜单id数组绑定到当前的这个角色id上去
        List<Integer> menuIdsCopy = CollUtil.newArrayList(menuIds);
        for (Integer menuId : menuIds) {
            Menu menu = menuService.getById(menuId);
            if (menu.getPid() != null && !menuIdsCopy.contains(menu.getPid())) { // 二级菜单 并且传过来的menuId数组里面没有它的父级id
                // 那么我们就得补上这个父级id
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menu.getPid());
                roleMenuMapper.insert(roleMenu);
                menuIdsCopy.add(menu.getPid());
            }
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuMapper.selectByRoleId(roleId);
    }

    @Override
    public boolean saveOrUpdate(Role role) {
        if (role.getId() == null) {
            roleMapper.insert(role);
            return true;
        }
        roleMapper.update(role);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return roleMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        roleMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    public List<Role> list() {
        return roleMapper.selectList(new Role());
    }

    @Override
    public List<Role> list(Role role) {
        return roleMapper.selectList(role);
    }

    @Override
    public Role getById(Integer id) {
        return roleMapper.getById(id);
    }

    @Override
    public PageInfo<Role> page(int pageNum, int pageSize, Role role) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles = roleMapper.selectList(role);
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Role> roleList) {
        for (Role role : roleList) {
            if (role.getId() == null) {
                roleMapper.insert(role);
                continue;
            }
            roleMapper.update(role);
        }
        return true;
    }
}
