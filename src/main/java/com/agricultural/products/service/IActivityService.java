package com.agricultural.products.service;

import com.agricultural.products.entity.Activity;
import com.agricultural.products.entity.dto.ActivityDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 活动信息服务类
 *
 */
public interface IActivityService {

    /**
     * 保存活动信息
     * @param activity
     * @return
     */
    boolean saveOrUpdate(Activity activity);

    /**
     * 移除活动信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除活动信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询活动信息
     * @return
     */
    List<ActivityDTO> list(Activity activity);

    /**
     * 根据id获取活动信息
     * @param id
     * @return
     */
    ActivityDTO getById(Integer id);

    /**
     * 活动信息分页
     * @param activityDTO
     * @return
     */
    PageInfo<ActivityDTO> page(ActivityDTO activityDTO);


    /**
     * 批量保存活动信息
     * @param activityList
     * @return
     */
    boolean saveBatch(Collection<Activity> activityList);
}
