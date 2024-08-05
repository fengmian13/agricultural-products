package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Supplier;
import com.agricultural.products.entity.dto.SupplierDTO;
import com.agricultural.products.mapper.SupplierMapper;
import com.agricultural.products.service.ISupplierService;
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
 * 供应商信息
 * 服务实现类
 *
 */
@Service
public class SupplierServiceImpl implements ISupplierService {


    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public boolean saveOrUpdate(Supplier supplier) {
        if (supplier.getId() == null) {
            supplier.setCreateTime(new Date());
            supplier.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            supplierMapper.insert(supplier);
            return true;
        }
        supplierMapper.update(supplier);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return supplierMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        supplierMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<SupplierDTO> list(Supplier supplier) {
        return supplierMapper.selectList(supplier);
    }

    @Override
    public SupplierDTO getById(Integer id) {
        return supplierMapper.getById(id);
    }

    @Override
    public PageInfo<SupplierDTO> page(SupplierDTO supplierDTO) {
        PageHelper.startPage(supplierDTO.getPageNum(), supplierDTO.getPageSize());
        Supplier supplier = BeanUtil.toBean(supplierDTO, Supplier.class);
        List<SupplierDTO> suppliers = supplierMapper.selectList(supplier);
        PageInfo<SupplierDTO> pageInfo = new PageInfo<>(suppliers);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Supplier> supplierList) {
        for (Supplier supplier : supplierList) {
            if (supplier.getId() == null) {
                supplierMapper.insert(supplier);
                continue;
            }
            supplierMapper.update(supplier);
        }
        return true;
    }
}
