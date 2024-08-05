package com.agricultural.products.service;

import com.agricultural.products.entity.Fertilization;
import com.agricultural.products.entity.dto.FertilizationDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 施肥信息服务类
 *
 */
public interface IFertilizationService {

    /**
     * 保存施肥信息
     * @param fertilization
     * @return
     */
    boolean saveOrUpdate(Fertilization fertilization);

    /**
     * 移除施肥信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除施肥信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询施肥信息
     * @return
     */
    List<FertilizationDTO> list(Fertilization fertilization);

    /**
     * 根据id获取施肥信息
     * @param id
     * @return
     */
    FertilizationDTO getById(Integer id);

    /**
     * 施肥信息分页
     * @param fertilizationDTO
     * @return
     */
    PageInfo<FertilizationDTO> page(FertilizationDTO fertilizationDTO);


    /**
     * 批量保存施肥信息
     * @param fertilizationList
     * @return
     */
    boolean saveBatch(Collection<Fertilization> fertilizationList);
}
