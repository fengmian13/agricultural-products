package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Dept;
import com.agricultural.products.entity.dto.DeptDTO;
import com.agricultural.products.mapper.DeptMapper;
import com.agricultural.products.service.IDeptService;
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
 * 部门信息
 * 服务实现类
 *
 */
@Service
public class DeptServiceImpl implements IDeptService {


    @Resource
    private DeptMapper deptMapper;

    @Override
    public boolean saveOrUpdate(Dept dept) {
        if (dept.getId() == null) {
            dept.setCreateTime(new Date());
            dept.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            deptMapper.insert(dept);
            return true;
        }
        deptMapper.update(dept);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return deptMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        deptMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<DeptDTO> list(Dept dept) {
        return deptMapper.selectList(dept);
    }

    @Override
    public DeptDTO getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public PageInfo<DeptDTO> page(DeptDTO deptDTO) {
        PageHelper.startPage(deptDTO.getPageNum(), deptDTO.getPageSize());
        Dept dept = BeanUtil.toBean(deptDTO, Dept.class);
        List<DeptDTO> depts = deptMapper.selectList(dept);
        PageInfo<DeptDTO> pageInfo = new PageInfo<>(depts);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Dept> deptList) {
        for (Dept dept : deptList) {
            if (dept.getId() == null) {
                deptMapper.insert(dept);
                continue;
            }
            deptMapper.update(dept);
        }
        return true;
    }
}
