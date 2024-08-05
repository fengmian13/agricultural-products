package com.agricultural.products.service.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.agricultural.products.entity.Fertilization;
import com.agricultural.products.entity.Harvest;
import com.agricultural.products.entity.Product;
import com.agricultural.products.entity.Water;
import com.agricultural.products.entity.dto.FertilizationDTO;
import com.agricultural.products.entity.dto.HarvestDTO;
import com.agricultural.products.entity.dto.SowingDTO;
import com.agricultural.products.entity.dto.WaterDTO;
import com.agricultural.products.mapper.*;
import com.agricultural.products.service.IHarvestService;
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
 * 收割信息
 * 服务实现类
 *
 */
@Service
public class HarvestServiceImpl implements IHarvestService {


    @Resource
    private HarvestMapper harvestMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private SowingMapper sowingMapper;
    @Resource
    private WaterMapper waterMapper;
    @Resource
    private FertilizationMapper fertilizationMapper;

    @Override
    @Transactional
    public boolean saveOrUpdate(Harvest harvest) {
        if (harvest.getId() == null) {
            harvest.setCreateTime(new Date());
            harvest.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            harvestMapper.insert(harvest);

            // 收割后生成农产品信息
            // 播种数据
            SowingDTO sowing = sowingMapper.getById(harvest.getSid());
            // 更新收割状态
            sowing.setStatus("已收割");
            sowingMapper.update(sowing);

            // 浇水数据
            Water water = new Water();
            water.setSid(harvest.getSid());
            List<WaterDTO> waterList = waterMapper.selectList(water);

            // 施肥数据
            Fertilization fertilization = new Fertilization();
            fertilization.setSid(harvest.getSid());
            List<FertilizationDTO> fertilizationDTOS = fertilizationMapper.selectList(fertilization);

            Product product = new Product();
            product.setSid(harvest.getSid());
            String name = CharSequenceUtil.replace(sowing.getSeedType(), "种子", "");
            product.setName(name);
            product.setArea(sowing.getArea());
            product.setSowingTime(sowing.getSowingTime());
            product.setHarvestTime(harvest.getHarvestTime());
            product.setFertilizationNum(fertilizationDTOS.size());
            product.setWaterNum(waterList.size());
            product.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            productMapper.insert(product);
            return true;
        }
        harvestMapper.update(harvest);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return harvestMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        harvestMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<HarvestDTO> list(Harvest harvest) {
        return harvestMapper.selectList(harvest);
    }

    @Override
    public HarvestDTO getById(Integer id) {
        return harvestMapper.getById(id);
    }

    @Override
    public PageInfo<HarvestDTO> page(HarvestDTO harvestDTO) {
        PageHelper.startPage(harvestDTO.getPageNum(), harvestDTO.getPageSize());
        Harvest harvest = BeanUtil.toBean(harvestDTO, Harvest.class);
        List<HarvestDTO> harvests = harvestMapper.selectList(harvest);
        PageInfo<HarvestDTO> pageInfo = new PageInfo<>(harvests);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Harvest> harvestList) {
        for (Harvest harvest : harvestList) {
            if (harvest.getId() == null) {
                harvestMapper.insert(harvest);
                continue;
            }
            harvestMapper.update(harvest);
        }
        return true;
    }
}
