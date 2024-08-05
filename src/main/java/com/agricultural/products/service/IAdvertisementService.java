package com.agricultural.products.service;

import com.agricultural.products.entity.Advertisement;
import com.agricultural.products.entity.dto.AdvertisementDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 广告信息服务类
 *
 */
public interface IAdvertisementService {

    /**
     * 保存广告信息
     * @param advertisement
     * @return
     */
    boolean saveOrUpdate(Advertisement advertisement);

    /**
     * 移除广告信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除广告信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询广告信息
     * @return
     */
    List<AdvertisementDTO> list(Advertisement advertisement);

    /**
     * 根据id获取广告信息
     * @param id
     * @return
     */
    AdvertisementDTO getById(Integer id);

    /**
     * 广告信息分页
     * @param advertisementDTO
     * @return
     */
    PageInfo<AdvertisementDTO> page(AdvertisementDTO advertisementDTO);


    /**
     * 批量保存广告信息
     * @param advertisementList
     * @return
     */
    boolean saveBatch(Collection<Advertisement> advertisementList);
}
