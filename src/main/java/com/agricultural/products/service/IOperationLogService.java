package com.agricultural.products.service;

import com.agricultural.products.entity.OperationLog;
import com.agricultural.products.entity.dto.OperationLogDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 操作日志信息服务类
 *
 */
public interface IOperationLogService {

    /**
     * 保存操作日志信息
     * @param operationLog
     * @return
     */
    boolean saveOrUpdate(OperationLog operationLog);

    /**
     * 移除操作日志信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除操作日志信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询操作日志信息
     * @return
     */
    List<OperationLogDTO> list(OperationLog operationLog);

    /**
     * 根据id获取操作日志信息
     * @param id
     * @return
     */
    OperationLogDTO getById(Integer id);

    /**
     * 操作日志信息分页
     * @param operationLogDTO
     * @return
     */
    PageInfo<OperationLogDTO> page(OperationLogDTO operationLogDTO);


    /**
     * 批量保存操作日志信息
     * @param operationLogList
     * @return
     */
    boolean saveBatch(Collection<OperationLog> operationLogList);
}
