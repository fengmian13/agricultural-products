package com.agricultural.products.mapper;

import com.agricultural.products.entity.Supplier;
import com.agricultural.products.entity.dto.SupplierDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 供应商信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface SupplierMapper {

    int insert(Supplier supplier);

    int update(Supplier supplier);

    List<SupplierDTO> selectList(@Param("supplier") Supplier supplier);

    SupplierDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
