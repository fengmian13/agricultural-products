package com.agricultural.products.service;

import com.agricultural.products.entity.Menu;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 服务类
 *
 */
public interface IMenuService {

    /**
     * 保存
     * @param menu
     * @return
     */
    boolean saveOrUpdate(Menu menu);

    /**
     * 移除
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);


    /**
     * 查询所有
     * @return
     */
    List<Menu> list();


    /**
     * 条件查询
     * @return
     */
    List<Menu> list(Menu menu);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Menu getById(Integer id);

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param menu
     * @return
     */
    PageInfo<Menu> page(int pageNum, int pageSize, Menu menu);


    /**
     * 批量保存
     * @param menuList
     * @return
     */
    boolean saveBatch(Collection<Menu> menuList);

    List<Menu> findMenus(String name);
}
