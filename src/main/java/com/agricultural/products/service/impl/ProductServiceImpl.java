package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Product;
import com.agricultural.products.entity.dto.ProductDTO;
import com.agricultural.products.mapper.ProductMapper;
import com.agricultural.products.service.IProductService;
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
 * 农产品信息
 * 服务实现类
 *
 */
@Service
public class ProductServiceImpl implements IProductService {


    @Resource
    private ProductMapper productMapper;

    @Override
    public boolean saveOrUpdate(Product product) {
        if (product.getId() == null) {
            product.setCreateTime(new Date());
            product.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            productMapper.insert(product);
            return true;
        }
        productMapper.update(product);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return productMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        productMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<ProductDTO> list(Product product) {
        return productMapper.selectList(product);
    }

    @Override
    public ProductDTO getById(Integer id) {
        return productMapper.getById(id);
    }

    @Override
    public PageInfo<ProductDTO> page(ProductDTO productDTO) {
        PageHelper.startPage(productDTO.getPageNum(), productDTO.getPageSize());
        Product product = BeanUtil.toBean(productDTO, Product.class);
        List<ProductDTO> products = productMapper.selectList(product);
        PageInfo<ProductDTO> pageInfo = new PageInfo<>(products);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Product> productList) {
        for (Product product : productList) {
            if (product.getId() == null) {
                productMapper.insert(product);
                continue;
            }
            productMapper.update(product);
        }
        return true;
    }
}
