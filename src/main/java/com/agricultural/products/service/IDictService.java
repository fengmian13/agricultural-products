package com.agricultural.products.service;

import com.agricultural.products.entity.Dict;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 服务类
 *
 */
public interface IDictService {

    /**
     * 保存
     * @param dict
     * @return
     */
    boolean saveOrUpdate(Dict dict);

    /**
     * 移除
     * @param id
     * @return
     */
    boolean removeById(Integer id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean removeByIds(Collection<Integer> ids);


    /**
     * 查询所有
     * @return
     */
    List<Dict> list();

    /**
     * 条件查询
     * @return
     */
    List<Dict> list(Dict dict);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Dict getById(Integer id);

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param dict
     * @return
     */
    PageInfo<Dict> page(int pageNum, int pageSize, Dict dict);


    /**
     * 批量保存
     * @param dictList
     * @return
     */
    boolean saveBatch(Collection<Dict> dictList);
}
