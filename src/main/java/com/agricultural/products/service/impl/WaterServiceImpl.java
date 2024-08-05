package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Water;
import com.agricultural.products.entity.dto.WaterDTO;
import com.agricultural.products.mapper.WaterMapper;
import com.agricultural.products.service.IWaterService;
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
 * 浇水信息
 * 服务实现类
 *
 */
@Service
public class WaterServiceImpl implements IWaterService {


    @Resource
    private WaterMapper waterMapper;

    @Override
    public boolean saveOrUpdate(Water water) {
        if (water.getId() == null) {
            water.setCreateTime(new Date());
            water.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            waterMapper.insert(water);
            return true;
        }
        waterMapper.update(water);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return waterMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        waterMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<WaterDTO> list(Water water) {
        return waterMapper.selectList(water);
    }

    @Override
    public WaterDTO getById(Integer id) {
        return waterMapper.getById(id);
    }

    @Override
    public PageInfo<WaterDTO> page(WaterDTO waterDTO) {
        PageHelper.startPage(waterDTO.getPageNum(), waterDTO.getPageSize());
        Water water = BeanUtil.toBean(waterDTO, Water.class);
        List<WaterDTO> waters = waterMapper.selectList(water);
        PageInfo<WaterDTO> pageInfo = new PageInfo<>(waters);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Water> waterList) {
        for (Water water : waterList) {
            if (water.getId() == null) {
                waterMapper.insert(water);
                continue;
            }
            waterMapper.update(water);
        }
        return true;
    }
}
