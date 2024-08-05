package com.agricultural.products.mapper;

import com.agricultural.products.entity.FarmTool;
import com.agricultural.products.entity.dto.FarmToolDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 农具信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface FarmToolMapper {

    int insert(FarmTool farmTool);

    int update(FarmTool farmTool);

    List<FarmToolDTO> selectList(@Param("farmTool") FarmTool farmTool);

    FarmToolDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
