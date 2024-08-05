package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Activity;
import com.agricultural.products.entity.dto.ActivityDTO;
import com.agricultural.products.mapper.ActivityMapper;
import com.agricultural.products.service.IActivityService;
import com.agricultural.products.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.bean.BeanUtil;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * 活动信息
 * 服务实现类
 *
 */
@Service
public class ActivityServiceImpl implements IActivityService {


    @Resource
    private ActivityMapper activityMapper;

    @Override
    public boolean saveOrUpdate(Activity activity) {
        if (activity.getId() == null) {
            activity.setCreateTime(new Date());
            activity.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            activityMapper.insert(activity);
            return true;
        }
        activityMapper.update(activity);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return activityMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        activityMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<ActivityDTO> list(Activity activity) {
        return activityMapper.selectList(activity);
    }

    @Override
    public ActivityDTO getById(Integer id) {
        return activityMapper.getById(id);
    }

    @Override
    public PageInfo<ActivityDTO> page(ActivityDTO activityDTO) {
        PageHelper.startPage(activityDTO.getPageNum(), activityDTO.getPageSize());
        Activity activity = BeanUtil.toBean(activityDTO, Activity.class);
        List<ActivityDTO> activitys = activityMapper.selectList(activity);
        PageInfo<ActivityDTO> pageInfo = new PageInfo<>(activitys);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Activity> activityList) {
        for (Activity activity : activityList) {
            if (activity.getId() == null) {
                activityMapper.insert(activity);
                continue;
            }
            activityMapper.update(activity);
        }
        return true;
    }
}
