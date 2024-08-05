package com.agricultural.products.mapper;

import com.agricultural.products.entity.Advertisement;
import com.agricultural.products.entity.dto.AdvertisementDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 广告信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface AdvertisementMapper {

    int insert(Advertisement advertisement);

    int update(Advertisement advertisement);

    List<AdvertisementDTO> selectList(@Param("advertisement") Advertisement advertisement);

    AdvertisementDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
