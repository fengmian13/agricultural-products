package com.agricultural.products.service;

import com.agricultural.products.entity.Dept;
import com.agricultural.products.entity.dto.DeptDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 部门信息服务类
 *
 */
public interface IDeptService {

    /**
     * 保存部门信息
     * @param dept
     * @return
     */
    boolean saveOrUpdate(Dept dept);

    /**
     * 移除部门信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除部门信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询部门信息
     * @return
     */
    List<DeptDTO> list(Dept dept);

    /**
     * 根据id获取部门信息
     * @param id
     * @return
     */
    DeptDTO getById(Integer id);

    /**
     * 部门信息分页
     * @param deptDTO
     * @return
     */
    PageInfo<DeptDTO> page(DeptDTO deptDTO);


    /**
     * 批量保存部门信息
     * @param deptList
     * @return
     */
    boolean saveBatch(Collection<Dept> deptList);
}
