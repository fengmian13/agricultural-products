package com.agricultural.products.mapper;

import com.agricultural.products.entity.Dept;
import com.agricultural.products.entity.dto.DeptDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 部门信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface DeptMapper {

    int insert(Dept dept);

    int update(Dept dept);

    List<DeptDTO> selectList(@Param("dept") Dept dept);

    DeptDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
