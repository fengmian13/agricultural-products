package com.agricultural.products.service;

import com.agricultural.products.entity.Staff;
import com.agricultural.products.entity.dto.StaffDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 员工信息服务类
 *
 */
public interface IStaffService {

    /**
     * 保存员工信息
     * @param staff
     * @return
     */
    boolean saveOrUpdate(Staff staff);

    /**
     * 移除员工信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除员工信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询员工信息
     * @return
     */
    List<StaffDTO> list(Staff staff);

    /**
     * 根据id获取员工信息
     * @param id
     * @return
     */
    StaffDTO getById(Integer id);

    /**
     * 员工信息分页
     * @param staffDTO
     * @return
     */
    PageInfo<StaffDTO> page(StaffDTO staffDTO);


    /**
     * 批量保存员工信息
     * @param staffList
     * @return
     */
    boolean saveBatch(Collection<Staff> staffList);
}
