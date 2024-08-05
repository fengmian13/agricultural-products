package com.agricultural.products.mapper;

import com.agricultural.products.entity.Product;
import com.agricultural.products.entity.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 农产品信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface ProductMapper {

    int insert(Product product);

    int update(Product product);

    List<ProductDTO> selectList(@Param("product") Product product);

    ProductDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
