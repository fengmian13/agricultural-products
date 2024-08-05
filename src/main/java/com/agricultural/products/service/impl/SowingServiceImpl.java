package com.agricultural.products.service.impl;

import com.agricultural.products.common.Constants;
import com.agricultural.products.entity.Seed;
import com.agricultural.products.entity.Sowing;
import com.agricultural.products.entity.dto.SeedDTO;
import com.agricultural.products.entity.dto.SowingDTO;
import com.agricultural.products.exception.ServiceException;
import com.agricultural.products.mapper.SeedMapper;
import com.agricultural.products.mapper.SowingMapper;
import com.agricultural.products.service.ISowingService;
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
 * 播种信息
 * 服务实现类
 *
 */
@Service
public class SowingServiceImpl implements ISowingService {


    @Resource
    private SowingMapper sowingMapper;
    @Resource
    private SeedMapper seedMapper;

    @Override
    public boolean saveOrUpdate(Sowing sowing) {
        if (sowing.getId() == null) {
            sowing.setStatus("待收割");
            sowing.setCreateTime(new Date());
            sowing.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            sowingMapper.insert(sowing);

            // 播种后种子数量需要减少
            if (sowing.getSid() != null){
                reduceNum(1, sowing.getSid());
            }
            return true;
        }

        // 更新数据时 若原来的种子与现在的种子不一样 需要减少当前的种子数量  新增原来的种子数量
        SowingDTO sowingDTO = sowingMapper.getById(sowing.getId());
        if (sowingDTO.getSid() != sowing.getSid()){
            reduceNum(1, sowing.getSid());
            if (sowingDTO.getSid() != null){
                addNum(1, sowingDTO.getSid());
            }
        }
        sowingMapper.update(sowing);
        return true;
    }

    /**
     * 新增种子的库存
     * @param num
     * @param sid
     */
    public void addNum(Integer num, Integer sid){
        SeedDTO seedDTO = seedMapper.getById(sid);
        if (seedDTO == null){
            throw new ServiceException(Constants.CODE_600, "种子不存在!");
        }
        seedDTO.setSeedNum(seedDTO.getSeedNum() + num);
        Seed seed = BeanUtil.toBean(seedDTO, Seed.class);
        seedMapper.update(seed);
    }

    /**
     * 减少种子的库存
     * @param num
     * @param sid
     */
    public void reduceNum(Integer num, Integer sid){
        SeedDTO seedDTO = seedMapper.getById(sid);
        if (seedDTO == null){
            throw new ServiceException(Constants.CODE_600, "种子不存在!");
        }
        if (seedDTO.getSeedNum() < num){
            throw new ServiceException(Constants.CODE_600, "种子数量不足!");
        }
        seedDTO.setSeedNum(seedDTO.getSeedNum() - num);
        Seed seed = BeanUtil.toBean(seedDTO, Seed.class);
        seedMapper.update(seed);
    }

    @Override
    public boolean removeById(Integer id) {
        return sowingMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        sowingMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<SowingDTO> list(Sowing sowing) {
        return sowingMapper.selectList(sowing);
    }

    @Override
    public SowingDTO getById(Integer id) {
        return sowingMapper.getById(id);
    }

    @Override
    public PageInfo<SowingDTO> page(SowingDTO sowingDTO) {
        PageHelper.startPage(sowingDTO.getPageNum(), sowingDTO.getPageSize());
        Sowing sowing = BeanUtil.toBean(sowingDTO, Sowing.class);
        List<SowingDTO> sowings = sowingMapper.selectList(sowing);
        PageInfo<SowingDTO> pageInfo = new PageInfo<>(sowings);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Sowing> sowingList) {
        for (Sowing sowing : sowingList) {
            if (sowing.getId() == null) {
                sowingMapper.insert(sowing);
                continue;
            }
            sowingMapper.update(sowing);
        }
        return true;
    }
}
