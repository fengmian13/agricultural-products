package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Seed;
import com.agricultural.products.entity.dto.SeedDTO;
import com.agricultural.products.mapper.SeedMapper;
import com.agricultural.products.service.ISeedService;
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
 * 种子信息
 * 服务实现类
 *
 */
@Service
public class SeedServiceImpl implements ISeedService {


    @Resource
    private SeedMapper seedMapper;

    @Override
    public boolean saveOrUpdate(Seed seed) {
        if (seed.getId() == null) {
            seed.setCreateTime(new Date());
            seed.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            seedMapper.insert(seed);
            return true;
        }
        seedMapper.update(seed);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return seedMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        seedMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<SeedDTO> list(Seed seed) {
        return seedMapper.selectList(seed);
    }

    @Override
    public SeedDTO getById(Integer id) {
        return seedMapper.getById(id);
    }

    @Override
    public PageInfo<SeedDTO> page(SeedDTO seedDTO) {
        PageHelper.startPage(seedDTO.getPageNum(), seedDTO.getPageSize());
        Seed seed = BeanUtil.toBean(seedDTO, Seed.class);
        List<SeedDTO> seeds = seedMapper.selectList(seed);
        PageInfo<SeedDTO> pageInfo = new PageInfo<>(seeds);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Seed> seedList) {
        for (Seed seed : seedList) {
            if (seed.getId() == null) {
                seedMapper.insert(seed);
                continue;
            }
            seedMapper.update(seed);
        }
        return true;
    }
}
