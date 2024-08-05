package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Staff;
import com.agricultural.products.entity.dto.StaffDTO;
import com.agricultural.products.mapper.StaffMapper;
import com.agricultural.products.service.IStaffService;
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
 * 员工信息
 * 服务实现类
 *
 */
@Service
public class StaffServiceImpl implements IStaffService {


    @Resource
    private StaffMapper staffMapper;

    @Override
    public boolean saveOrUpdate(Staff staff) {
        if (staff.getId() == null) {
            staff.setCreateTime(new Date());
            staff.setCreateUser(TokenUtils.getCurrentUser().getUsername());
            staffMapper.insert(staff);
            return true;
        }
        staffMapper.update(staff);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return staffMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        staffMapper.deleteBatchIds(ids);
        return true;
    }


    @Override
    public List<StaffDTO> list(Staff staff) {
        return staffMapper.selectList(staff);
    }

    @Override
    public StaffDTO getById(Integer id) {
        return staffMapper.getById(id);
    }

    @Override
    public PageInfo<StaffDTO> page(StaffDTO staffDTO) {
        PageHelper.startPage(staffDTO.getPageNum(), staffDTO.getPageSize());
        Staff staff = BeanUtil.toBean(staffDTO, Staff.class);
        List<StaffDTO> staffs = staffMapper.selectList(staff);
        PageInfo<StaffDTO> pageInfo = new PageInfo<>(staffs);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Staff> staffList) {
        for (Staff staff : staffList) {
            if (staff.getId() == null) {
                staffMapper.insert(staff);
                continue;
            }
            staffMapper.update(staff);
        }
        return true;
    }
}
