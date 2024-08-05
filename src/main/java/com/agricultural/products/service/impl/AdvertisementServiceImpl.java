package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Advertisement;
import com.agricultural.products.entity.dto.AdvertisementDTO;
import com.agricultural.products.mapper.AdvertisementMapper;
import com.agricultural.products.service.IAdvertisementService;
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
 * 广告信息
 * 服务实现类
 *
 */
@Service
public class AdvertisementServiceImpl implements IAdvertisementService {


    @Resource
    private AdvertisementMapper advertisementMapper;

    @Override
    public boolean saveOrUpdate(Advertisement advertisement) {
        if (advertisement.getId() == null) {
            advertisement.setCreateTime(new Date());
            advertisement.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            advertisementMapper.insert(advertisement);
            return true;
        }
        advertisementMapper.update(advertisement);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return advertisementMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        advertisementMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<AdvertisementDTO> list(Advertisement advertisement) {
        return advertisementMapper.selectList(advertisement);
    }

    @Override
    public AdvertisementDTO getById(Integer id) {
        return advertisementMapper.getById(id);
    }

    @Override
    public PageInfo<AdvertisementDTO> page(AdvertisementDTO advertisementDTO) {
        PageHelper.startPage(advertisementDTO.getPageNum(), advertisementDTO.getPageSize());
        Advertisement advertisement = BeanUtil.toBean(advertisementDTO, Advertisement.class);
        List<AdvertisementDTO> advertisements = advertisementMapper.selectList(advertisement);
        PageInfo<AdvertisementDTO> pageInfo = new PageInfo<>(advertisements);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Advertisement> advertisementList) {
        for (Advertisement advertisement : advertisementList) {
            if (advertisement.getId() == null) {
                advertisementMapper.insert(advertisement);
                continue;
            }
            advertisementMapper.update(advertisement);
        }
        return true;
    }
}
