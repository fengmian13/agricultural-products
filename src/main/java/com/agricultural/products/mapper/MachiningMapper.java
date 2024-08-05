package com.agricultural.products.mapper;

import com.agricultural.products.entity.Machining;
import com.agricultural.products.entity.dto.MachiningDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 加工信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface MachiningMapper {

    int insert(Machining machining);

    int update(Machining machining);

    List<MachiningDTO> selectList(@Param("machining") Machining machining);

    MachiningDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
