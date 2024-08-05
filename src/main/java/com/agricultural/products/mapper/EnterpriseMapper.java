package com.agricultural.products.mapper;

import com.agricultural.products.entity.Enterprise;
import com.agricultural.products.entity.dto.EnterpriseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 企业信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface EnterpriseMapper {

    int insert(Enterprise enterprise);

    int update(Enterprise enterprise);

    List<EnterpriseDTO> selectList(@Param("enterprise") Enterprise enterprise);

    EnterpriseDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
