package com.agricultural.products.service;

import com.agricultural.products.entity.Supplier;
import com.agricultural.products.entity.dto.SupplierDTO;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 供应商信息服务类
 *
 */
public interface ISupplierService {

    /**
     * 保存供应商信息
     * @param supplier
     * @return
     */
    boolean saveOrUpdate(Supplier supplier);

    /**
     * 移除供应商信息
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除供应商信息
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);

    /**
     * 条件查询供应商信息
     * @return
     */
    List<SupplierDTO> list(Supplier supplier);

    /**
     * 根据id获取供应商信息
     * @param id
     * @return
     */
    SupplierDTO getById(Integer id);

    /**
     * 供应商信息分页
     * @param supplierDTO
     * @return
     */
    PageInfo<SupplierDTO> page(SupplierDTO supplierDTO);


    /**
     * 批量保存供应商信息
     * @param supplierList
     * @return
     */
    boolean saveBatch(Collection<Supplier> supplierList);
}
