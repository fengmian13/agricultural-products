package com.agricultural.products.service;

import com.agricultural.products.entity.Enterprise;
import com.agricultural.products.entity.dto.EnterpriseDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 企业信息服务类
 *
 */
public interface IEnterpriseService {

    /**
     * 保存企业信息
     * @param enterprise
     * @return
     */
    boolean saveOrUpdate(Enterprise enterprise);

    /**
     * 移除企业信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除企业信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询企业信息
     * @return
     */
    List<EnterpriseDTO> list(Enterprise enterprise);

    /**
     * 根据id获取企业信息
     * @param id
     * @return
     */
    EnterpriseDTO getById(Integer id);

    /**
     * 企业信息分页
     * @param enterpriseDTO
     * @return
     */
    PageInfo<EnterpriseDTO> page(EnterpriseDTO enterpriseDTO);


    /**
     * 批量保存企业信息
     * @param enterpriseList
     * @return
     */
    boolean saveBatch(Collection<Enterprise> enterpriseList);
}
