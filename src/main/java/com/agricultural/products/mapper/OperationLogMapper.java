package com.agricultural.products.mapper;

import com.agricultural.products.entity.OperationLog;
import com.agricultural.products.entity.dto.OperationLogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 *
 * 操作日志信息 持久层 Mapper 接口
 *
 */
@Mapper
public interface OperationLogMapper {

    int insert(OperationLog operationLog);

    int update(OperationLog operationLog);

    List<OperationLogDTO> selectList(@Param("operationLog") OperationLog operationLog);

    OperationLogDTO getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
