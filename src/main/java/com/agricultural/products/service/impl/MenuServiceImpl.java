package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Menu;
import com.agricultural.products.mapper.MenuMapper;
import com.agricultural.products.service.IMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 系统菜单服务实现类
 *
 */
@Service
public class MenuServiceImpl implements IMenuService {


    @Resource
    private MenuMapper menuMapper;

    @Override
    public boolean saveOrUpdate(Menu menu) {
        if (menu.getId() == null) {
            menuMapper.insert(menu);
            return true;
        }
        menuMapper.update(menu);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return menuMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        menuMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    public List<Menu> list() {
        return menuMapper.selectList(new Menu());
    }

    @Override
    public List<Menu> list(Menu menu) {
        return menuMapper.selectList(menu);
    }

    @Override
    public Menu getById(Integer id) {
        return menuMapper.getById(id);
    }

    @Override
    public PageInfo<Menu> page(int pageNum, int pageSize, Menu menu) {
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> menus = menuMapper.selectList(menu);
        PageInfo<Menu> pageInfo = new PageInfo<>(menus);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Menu> menuList) {
        for (Menu menu : menuList) {
            if (menu.getId() == null) {
                menuMapper.insert(menu);
                continue;
            }
            menuMapper.update(menu);
        }
        return true;
    }

    @Override
    public List<Menu> findMenus(String name) {
        Menu menuQuery = new Menu();
        menuQuery.setName(name);
        // 查询所有数据
        List<Menu> list = list(menuQuery);
        list.sort(Comparator.comparing(Menu::getSortNum));

        // 找出pid为null的一级菜单
        List<Menu> parentNodes = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        // 找出一级菜单的子菜单
        for (Menu menu : parentNodes) {
            // 筛选所有数据中pid=父级id的数据就是二级菜单
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return parentNodes;
    }
}
