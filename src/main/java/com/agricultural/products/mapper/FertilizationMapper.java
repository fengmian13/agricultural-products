package com.agricultural.products.mapper;

import com.agricultural.products.entity.Fertilization;
import com.agricultural.products.entity.dto.FertilizationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 施肥信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface FertilizationMapper {

    int insert(Fertilization fertilization);

    int update(Fertilization fertilization);

    List<FertilizationDTO> selectList(@Param("fertilization") Fertilization fertilization);

    FertilizationDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
