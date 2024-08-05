package com.agricultural.products.mapper;

import com.agricultural.products.entity.Activity;
import com.agricultural.products.entity.dto.ActivityDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 活动信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface ActivityMapper {

    int insert(Activity activity);

    int update(Activity activity);

    List<ActivityDTO> selectList(@Param("activity") Activity activity);

    ActivityDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
