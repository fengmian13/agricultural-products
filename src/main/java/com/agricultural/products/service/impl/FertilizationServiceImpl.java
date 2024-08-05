package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Fertilization;
import com.agricultural.products.entity.dto.FertilizationDTO;
import com.agricultural.products.mapper.FertilizationMapper;
import com.agricultural.products.service.IFertilizationService;
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
 * 施肥信息
 * 服务实现类
 *
 */
@Service
public class FertilizationServiceImpl implements IFertilizationService {


    @Resource
    private FertilizationMapper fertilizationMapper;

    @Override
    public boolean saveOrUpdate(Fertilization fertilization) {
        if (fertilization.getId() == null) {
            fertilization.setCreateTime(new Date());
            fertilization.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            fertilizationMapper.insert(fertilization);
            return true;
        }
        fertilizationMapper.update(fertilization);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return fertilizationMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        fertilizationMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<FertilizationDTO> list(Fertilization fertilization) {
        return fertilizationMapper.selectList(fertilization);
    }

    @Override
    public FertilizationDTO getById(Integer id) {
        return fertilizationMapper.getById(id);
    }

    @Override
    public PageInfo<FertilizationDTO> page(FertilizationDTO fertilizationDTO) {
        PageHelper.startPage(fertilizationDTO.getPageNum(), fertilizationDTO.getPageSize());
        Fertilization fertilization = BeanUtil.toBean(fertilizationDTO, Fertilization.class);
        List<FertilizationDTO> fertilizations = fertilizationMapper.selectList(fertilization);
        PageInfo<FertilizationDTO> pageInfo = new PageInfo<>(fertilizations);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Fertilization> fertilizationList) {
        for (Fertilization fertilization : fertilizationList) {
            if (fertilization.getId() == null) {
                fertilizationMapper.insert(fertilization);
                continue;
            }
            fertilizationMapper.update(fertilization);
        }
        return true;
    }
}
