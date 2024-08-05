package com.agricultural.products.mapper;

import com.agricultural.products.entity.Sowing;
import com.agricultural.products.entity.dto.SowingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 播种信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface SowingMapper {

    int insert(Sowing sowing);

    int update(Sowing sowing);

    List<SowingDTO> selectList(@Param("sowing") Sowing sowing);

    SowingDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
