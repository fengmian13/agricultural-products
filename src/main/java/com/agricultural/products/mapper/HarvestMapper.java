package com.agricultural.products.mapper;

import com.agricultural.products.entity.Harvest;
import com.agricultural.products.entity.dto.HarvestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 收割信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface HarvestMapper {

    int insert(Harvest harvest);

    int update(Harvest harvest);

    List<HarvestDTO> selectList(@Param("harvest") Harvest harvest);

    HarvestDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
