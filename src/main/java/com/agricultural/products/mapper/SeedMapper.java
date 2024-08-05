package com.agricultural.products.mapper;

import com.agricultural.products.entity.Seed;
import com.agricultural.products.entity.dto.SeedDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 种子信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface SeedMapper {

    int insert(Seed seed);

    int update(Seed seed);

    List<SeedDTO> selectList(@Param("seed") Seed seed);

    SeedDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
