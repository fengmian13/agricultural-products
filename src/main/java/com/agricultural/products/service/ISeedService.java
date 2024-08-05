package com.agricultural.products.service;

import com.agricultural.products.entity.Seed;
import com.agricultural.products.entity.dto.SeedDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 种子信息服务类
 *
 */
public interface ISeedService {

    /**
     * 保存种子信息
     * @param seed
     * @return
     */
    boolean saveOrUpdate(Seed seed);

    /**
     * 移除种子信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除种子信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询种子信息
     * @return
     */
    List<SeedDTO> list(Seed seed);

    /**
     * 根据id获取种子信息
     * @param id
     * @return
     */
    SeedDTO getById(Integer id);

    /**
     * 种子信息分页
     * @param seedDTO
     * @return
     */
    PageInfo<SeedDTO> page(SeedDTO seedDTO);


    /**
     * 批量保存种子信息
     * @param seedList
     * @return
     */
    boolean saveBatch(Collection<Seed> seedList);
}
