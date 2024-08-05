package com.agricultural.products.service;

import com.agricultural.products.entity.Machining;
import com.agricultural.products.entity.dto.MachiningDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 加工信息服务类
 *
 */
public interface IMachiningService {

    /**
     * 保存加工信息
     * @param machining
     * @return
     */
    boolean saveOrUpdate(Machining machining);

    /**
     * 移除加工信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除加工信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询加工信息
     * @return
     */
    List<MachiningDTO> list(Machining machining);

    /**
     * 根据id获取加工信息
     * @param id
     * @return
     */
    MachiningDTO getById(Integer id);

    /**
     * 加工信息分页
     * @param machiningDTO
     * @return
     */
    PageInfo<MachiningDTO> page(MachiningDTO machiningDTO);


    /**
     * 批量保存加工信息
     * @param machiningList
     * @return
     */
    boolean saveBatch(Collection<Machining> machiningList);
}
