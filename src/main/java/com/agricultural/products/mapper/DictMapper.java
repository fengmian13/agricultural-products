package com.agricultural.products.mapper;

import com.agricultural.products.entity.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DictMapper {

    int insert(Dict dict);

    int update(Dict dict);

    List<Dict> selectList(@Param("dict") Dict dict);

    Dict getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
