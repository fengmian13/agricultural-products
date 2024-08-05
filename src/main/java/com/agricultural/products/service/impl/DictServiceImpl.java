package com.agricultural.products.service.impl;

import com.agricultural.products.entity.Dict;
import com.agricultural.products.mapper.DictMapper;
import com.agricultural.products.service.IDictService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 *
 * 
 * 服务实现类
 *
 */
@Service
public class DictServiceImpl implements IDictService {


    @Resource
    private DictMapper dictMapper;

    @Override
    public boolean saveOrUpdate(Dict dict) {
        if (dict.getId() == null) {
            dictMapper.insert(dict);
            return true;
        }
        dictMapper.update(dict);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return dictMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        dictMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    public List<Dict> list() {
        return dictMapper.selectList(new Dict());
    }

    @Override
    public List<Dict> list(Dict dict) {
        return dictMapper.selectList(dict);
    }

    @Override
    public Dict getById(Integer id) {
        return dictMapper.getById(id);
    }

    @Override
    public PageInfo<Dict> page(int pageNum, int pageSize, Dict dict) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dict> dicts = dictMapper.selectList(dict);
        PageInfo<Dict> pageInfo = new PageInfo<>(dicts);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Dict> dictList) {
        for (Dict dict : dictList) {
            if (dict.getId() == null) {
                dictMapper.insert(dict);
                continue;
            }
            dictMapper.update(dict);
        }
        return true;
    }
}
