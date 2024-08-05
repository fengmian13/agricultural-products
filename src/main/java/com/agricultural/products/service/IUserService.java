package com.agricultural.products.service;

import com.agricultural.products.entity.dto.UserDTO;
import com.agricultural.products.entity.dto.UserPasswordDTO;
import com.agricultural.products.entity.User;
import com.github.pagehelper.PageInfo;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 服务类
 *
 */
public interface IUserService {

    /**
     * 保存
     * @param user
     * @return
     */
    boolean saveOrUpdate(User user);

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
    List<User> list();

    /**
     * 查询所有
     * @return
     */
    List<User> list(User user);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 分页
     * @param pageNum
     * @param pageSize
     * @param user
     * @return
     */
    PageInfo<User> page(int pageNum, int pageSize, User user);


    /**
     * 批量保存
     * @param userList
     * @return
     */
    boolean saveBatch(Collection<User> userList);


    /**
     * 登录
     * @param userDTO
     * @return
     */
    UserDTO login(UserDTO userDTO);

    /**
     * 注册
     * @param userDTO
     * @return
     */
    User register(UserDTO userDTO);

    /**
     * 修改密码
     * @param userPasswordDTO
     */
    void updatePassword(UserPasswordDTO userPasswordDTO);
}
