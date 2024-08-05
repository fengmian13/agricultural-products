package com.agricultural.products.service.impl;

import com.agricultural.products.entity.OperationLog;
import com.agricultural.products.entity.dto.OperationLogDTO;
import com.agricultural.products.mapper.OperationLogMapper;
import com.agricultural.products.service.IOperationLogService;
import com.agricultural.products.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.bean.BeanUtil;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * 操作日志信息
 * 服务实现类
 *
 */
@Service
public class OperationLogServiceImpl implements IOperationLogService {


    @Resource
    private OperationLogMapper operationLogMapper;

    @Override
    public boolean saveOrUpdate(OperationLog operationLog) {
        if (operationLog.getId() == null) {
            operationLog.setCreateTime(new Date());
            operationLog.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            operationLogMapper.insert(operationLog);
            return true;
        }
        operationLogMapper.update(operationLog);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return operationLogMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        operationLogMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<OperationLogDTO> list(OperationLog operationLog) {
        return operationLogMapper.selectList(operationLog);
    }

    @Override
    public OperationLogDTO getById(Integer id) {
        return operationLogMapper.getById(id);
    }

    @Override
    public PageInfo<OperationLogDTO> page(OperationLogDTO operationLogDTO) {
        PageHelper.startPage(operationLogDTO.getPageNum(), operationLogDTO.getPageSize());
        OperationLog operationLog = BeanUtil.toBean(operationLogDTO, OperationLog.class);
        List<OperationLogDTO> operationLogs = operationLogMapper.selectList(operationLog);
        PageInfo<OperationLogDTO> pageInfo = new PageInfo<>(operationLogs);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<OperationLog> operationLogList) {
        for (OperationLog operationLog : operationLogList) {
            if (operationLog.getId() == null) {
                operationLogMapper.insert(operationLog);
                continue;
            }
            operationLogMapper.update(operationLog);
        }
        return true;
    }
}
