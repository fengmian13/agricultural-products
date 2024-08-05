package com.agricultural.products.service;

import com.agricultural.products.entity.Water;
import com.agricultural.products.entity.dto.WaterDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 浇水信息服务类
 *
 */
public interface IWaterService {

    /**
     * 保存浇水信息
     * @param water
     * @return
     */
    boolean saveOrUpdate(Water water);

    /**
     * 移除浇水信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除浇水信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询浇水信息
     * @return
     */
    List<WaterDTO> list(Water water);

    /**
     * 根据id获取浇水信息
     * @param id
     * @return
     */
    WaterDTO getById(Integer id);

    /**
     * 浇水信息分页
     * @param waterDTO
     * @return
     */
    PageInfo<WaterDTO> page(WaterDTO waterDTO);


    /**
     * 批量保存浇水信息
     * @param waterList
     * @return
     */
    boolean saveBatch(Collection<Water> waterList);
}
