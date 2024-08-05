package com.agricultural.products.service;

import com.agricultural.products.entity.Product;
import com.agricultural.products.entity.dto.ProductDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 农产品信息服务类
 *
 */
public interface IProductService {

    /**
     * 保存农产品信息
     * @param product
     * @return
     */
    boolean saveOrUpdate(Product product);

    /**
     * 移除农产品信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除农产品信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询农产品信息
     * @return
     */
    List<ProductDTO> list(Product product);

    /**
     * 根据id获取农产品信息
     * @param id
     * @return
     */
    ProductDTO getById(Integer id);

    /**
     * 农产品信息分页
     * @param productDTO
     * @return
     */
    PageInfo<ProductDTO> page(ProductDTO productDTO);


    /**
     * 批量保存农产品信息
     * @param productList
     * @return
     */
    boolean saveBatch(Collection<Product> productList);
}
