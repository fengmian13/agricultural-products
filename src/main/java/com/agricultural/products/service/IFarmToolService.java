package com.agricultural.products.service;

import com.agricultural.products.entity.FarmTool;
import com.agricultural.products.entity.dto.FarmToolDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 农具信息服务类
 *
 */
public interface IFarmToolService {

    /**
     * 保存农具信息
     * @param farmTool
     * @return
     */
    boolean saveOrUpdate(FarmTool farmTool);

    /**
     * 移除农具信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除农具信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询农具信息
     * @return
     */
    List<FarmToolDTO> list(FarmTool farmTool);

    /**
     * 根据id获取农具信息
     * @param id
     * @return
     */
    FarmToolDTO getById(Integer id);

    /**
     * 农具信息分页
     * @param farmToolDTO
     * @return
     */
    PageInfo<FarmToolDTO> page(FarmToolDTO farmToolDTO);


    /**
     * 批量保存农具信息
     * @param farmToolList
     * @return
     */
    boolean saveBatch(Collection<FarmTool> farmToolList);
}
