package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Enterprise;
import com.agricultural.products.entity.dto.EnterpriseDTO;
import com.agricultural.products.mapper.EnterpriseMapper;
import com.agricultural.products.service.IEnterpriseService;
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
 * 企业信息
 * 服务实现类
 *
 */
@Service
public class EnterpriseServiceImpl implements IEnterpriseService {


    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    public boolean saveOrUpdate(Enterprise enterprise) {
        if (enterprise.getId() == null) {
            enterprise.setCreateTime(new Date());
            enterprise.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            enterpriseMapper.insert(enterprise);
            return true;
        }
        enterpriseMapper.update(enterprise);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return enterpriseMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        enterpriseMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<EnterpriseDTO> list(Enterprise enterprise) {
        return enterpriseMapper.selectList(enterprise);
    }

    @Override
    public EnterpriseDTO getById(Integer id) {
        return enterpriseMapper.getById(id);
    }

    @Override
    public PageInfo<EnterpriseDTO> page(EnterpriseDTO enterpriseDTO) {
        PageHelper.startPage(enterpriseDTO.getPageNum(), enterpriseDTO.getPageSize());
        Enterprise enterprise = BeanUtil.toBean(enterpriseDTO, Enterprise.class);
        List<EnterpriseDTO> enterprises = enterpriseMapper.selectList(enterprise);
        PageInfo<EnterpriseDTO> pageInfo = new PageInfo<>(enterprises);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Enterprise> enterpriseList) {
        for (Enterprise enterprise : enterpriseList) {
            if (enterprise.getId() == null) {
                enterpriseMapper.insert(enterprise);
                continue;
            }
            enterpriseMapper.update(enterprise);
        }
        return true;
    }
}
