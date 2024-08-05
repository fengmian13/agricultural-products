package com.agricultural.products.mapper;

import com.agricultural.products.entity.Water;
import com.agricultural.products.entity.dto.WaterDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 浇水信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface WaterMapper {

    int insert(Water water);

    int update(Water water);

    List<WaterDTO> selectList(@Param("water") Water water);

    WaterDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
