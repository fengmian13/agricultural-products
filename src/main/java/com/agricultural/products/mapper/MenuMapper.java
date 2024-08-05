package com.agricultural.products.mapper;

import com.agricultural.products.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 
 *  Mapper 接口
 *
 */
@Mapper
public interface MenuMapper {

    int insert(Menu menu);

    int update(Menu menu);

    List<Menu> selectList(@Param("menu") Menu menu);

    Menu getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
