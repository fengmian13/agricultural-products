package com.agricultural.products.service;

import com.agricultural.products.entity.Harvest;
import com.agricultural.products.entity.dto.HarvestDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 收割信息服务类
 *
 */
public interface IHarvestService {

    /**
     * 保存收割信息
     * @param harvest
     * @return
     */
    boolean saveOrUpdate(Harvest harvest);

    /**
     * 移除收割信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除收割信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询收割信息
     * @return
     */
    List<HarvestDTO> list(Harvest harvest);

    /**
     * 根据id获取收割信息
     * @param id
     * @return
     */
    HarvestDTO getById(Integer id);

    /**
     * 收割信息分页
     * @param harvestDTO
     * @return
     */
    PageInfo<HarvestDTO> page(HarvestDTO harvestDTO);


    /**
     * 批量保存收割信息
     * @param harvestList
     * @return
     */
    boolean saveBatch(Collection<Harvest> harvestList);
}
