package com.agricultural.products.mapper;

import com.agricultural.products.entity.Staff;
import com.agricultural.products.entity.dto.StaffDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 员工信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface StaffMapper {

    int insert(Staff staff);

    int update(Staff staff);

    List<StaffDTO> selectList(@Param("staff") Staff staff);

    StaffDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
