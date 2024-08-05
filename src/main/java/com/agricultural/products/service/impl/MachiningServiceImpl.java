package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Machining;
import com.agricultural.products.entity.dto.MachiningDTO;
import com.agricultural.products.mapper.MachiningMapper;
import com.agricultural.products.service.IMachiningService;
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
 * 加工信息
 * 服务实现类
 *
 */
@Service
public class MachiningServiceImpl implements IMachiningService {


    @Resource
    private MachiningMapper machiningMapper;

    @Override
    public boolean saveOrUpdate(Machining machining) {
        if (machining.getId() == null) {
            machining.setCreateTime(new Date());
            machining.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            machiningMapper.insert(machining);
            return true;
        }
        machiningMapper.update(machining);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return machiningMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        machiningMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<MachiningDTO> list(Machining machining) {
        return machiningMapper.selectList(machining);
    }

    @Override
    public MachiningDTO getById(Integer id) {
        return machiningMapper.getById(id);
    }

    @Override
    public PageInfo<MachiningDTO> page(MachiningDTO machiningDTO) {
        PageHelper.startPage(machiningDTO.getPageNum(), machiningDTO.getPageSize());
        Machining machining = BeanUtil.toBean(machiningDTO, Machining.class);
        List<MachiningDTO> machinings = machiningMapper.selectList(machining);
        PageInfo<MachiningDTO> pageInfo = new PageInfo<>(machinings);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Machining> machiningList) {
        for (Machining machining : machiningList) {
            if (machining.getId() == null) {
                machiningMapper.insert(machining);
                continue;
            }
            machiningMapper.update(machining);
        }
        return true;
    }
}
