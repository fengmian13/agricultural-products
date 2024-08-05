package com.agricultural.products.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.agricultural.products.entity.dto.UserDTO;
import com.agricultural.products.entity.dto.UserPasswordDTO;
import com.agricultural.products.common.Constants;
import com.agricultural.products.common.RoleEnum;
import com.agricultural.products.entity.Menu;
import com.agricultural.products.entity.User;
import com.agricultural.products.exception.ServiceException;
import com.agricultural.products.mapper.RoleMapper;
import com.agricultural.products.mapper.RoleMenuMapper;
import com.agricultural.products.mapper.UserMapper;
import com.agricultural.products.service.IMenuService;
import com.agricultural.products.service.IUserService;
import com.agricultural.products.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * 用户服务实现类
 *
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Log LOG = Log.get();

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);

            String role = one.getRole(); // ROLE_ADMIN
            // 设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User userQueryOne = new User();
        userQueryOne.setUsername(userDTO.getUsername());
        List<User> userOneList = userMapper.selectList(userQueryOne);
        User one;
        if (CollectionUtils.isEmpty(userOneList)) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            // 空角色默认一个普通用户的角色
            if (StringUtils.isBlank(one.getRole())){
                one.setRole(RoleEnum.ROLE_USER.toString());
            }
            one.setCreateTime(new Date());
            userMapper.insert(one);  // 把 copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.updatePassword(userPasswordDTO);
        if (update < 1) {
            throw new ServiceException(Constants.CODE_600, "密码错误");
        }
    }

    private User getUserInfo(UserDTO userDTO) {
        User one;
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        try {
            List<User> users = userMapper.selectList(user); // 从数据库查询用户信息
            if (CollectionUtils.isEmpty(users)) {
                return null;
            }
            else {
                one = users.get(0);
            }
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    /**
     * 获取当前角色的菜单列表
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 查出系统所有的菜单(树形)
        List<Menu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }

    @Override
    public boolean saveOrUpdate(User user) {
        if (user.getId() == null) {
            userMapper.insert(user);
            return true;
        }
        userMapper.update(user);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public boolean removeByIds(Collection<Integer> ids) {
        userMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    public List<User> list() {
        return userMapper.selectList(new User());
    }

    @Override
    public List<User> list(User user) {
        return userMapper.selectList(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public PageInfo<User> page(int pageNum, int pageSize, User user) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectList(user);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<User> userList) {
        for (User user : userList) {
            if (user.getId() == null) {
                userMapper.insert(user);
                continue;
            }
            userMapper.update(user);
        }
        return true;
    }

}
