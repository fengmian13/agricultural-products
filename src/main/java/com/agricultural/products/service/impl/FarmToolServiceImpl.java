package com.agricultural.products.service.impl;

import com.agricultural.products.entity.FarmTool;
import com.agricultural.products.entity.dto.FarmToolDTO;
import com.agricultural.products.mapper.FarmToolMapper;
import com.agricultural.products.service.IFarmToolService;
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
 * 农具信息
 * 服务实现类
 *
 */
@Service
public class FarmToolServiceImpl implements IFarmToolService {


    @Resource
    private FarmToolMapper farmToolMapper;

    @Override
    public boolean saveOrUpdate(FarmTool farmTool) {
        if (farmTool.getId() == null) {
            farmTool.setCreateTime(new Date());
            farmTool.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            farmToolMapper.insert(farmTool);
            return true;
        }
        farmToolMapper.update(farmTool);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return farmToolMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        farmToolMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<FarmToolDTO> list(FarmTool farmTool) {
        return farmToolMapper.selectList(farmTool);
    }

    @Override
    public FarmToolDTO getById(Integer id) {
        return farmToolMapper.getById(id);
    }

    @Override
    public PageInfo<FarmToolDTO> page(FarmToolDTO farmToolDTO) {
        PageHelper.startPage(farmToolDTO.getPageNum(), farmToolDTO.getPageSize());
        FarmTool farmTool = BeanUtil.toBean(farmToolDTO, FarmTool.class);
        List<FarmToolDTO> farmTools = farmToolMapper.selectList(farmTool);
        PageInfo<FarmToolDTO> pageInfo = new PageInfo<>(farmTools);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<FarmTool> farmToolList) {
        for (FarmTool farmTool : farmToolList) {
            if (farmTool.getId() == null) {
                farmToolMapper.insert(farmTool);
                continue;
            }
            farmToolMapper.update(farmTool);
        }
        return true;
    }
}
