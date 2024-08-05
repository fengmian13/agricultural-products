package com.agricultural.products.service;

import com.agricultural.products.entity.Role;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 服务类
 *
 */
public interface IRoleService {

    /**
     * 保存
     * @param role
     * @return
     */
    boolean saveOrUpdate(Role role);

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
    List<Role> list();


    /**
     * 条件查询
     * @return
     */
    List<Role> list(Role role);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Role getById(Integer id);

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param role
     * @return
     */
    PageInfo<Role> page(int pageNum, int pageSize, Role role);


    /**
     * 批量保存
     * @param roleList
     * @return
     */
    boolean saveBatch(Collection<Role> roleList);

    void setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer roleId);
}
