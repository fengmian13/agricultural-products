package com.agricultural.products.service;

import com.agricultural.products.entity.Sowing;
import com.agricultural.products.entity.dto.SowingDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 播种信息服务类
 *
 */
public interface ISowingService {

    /**
     * 保存播种信息
     * @param sowing
     * @return
     */
    boolean saveOrUpdate(Sowing sowing);

    /**
     * 移除播种信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除播种信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询播种信息
     * @return
     */
    List<SowingDTO> list(Sowing sowing);

    /**
     * 根据id获取播种信息
     * @param id
     * @return
     */
    SowingDTO getById(Integer id);

    /**
     * 播种信息分页
     * @param sowingDTO
     * @return
     */
    PageInfo<SowingDTO> page(SowingDTO sowingDTO);


    /**
     * 批量保存播种信息
     * @param sowingList
     * @return
     */
    boolean saveBatch(Collection<Sowing> sowingList);
}
