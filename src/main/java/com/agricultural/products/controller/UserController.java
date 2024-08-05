package com.agricultural.products.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.agricultural.products.annotation.Log;
import com.agricultural.products.common.Constants;
import com.agricultural.products.common.Result;
import com.agricultural.products.config.interceptor.AuthAccess;
import com.agricultural.products.entity.User;
import com.agricultural.products.entity.dto.UserDTO;
import com.agricultural.products.entity.dto.UserPasswordDTO;
import com.agricultural.products.exception.ServiceException;
import com.agricultural.products.service.IUserService;
import com.agricultural.products.utils.PageResult;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.register(userDTO));
    }


    /**
     * 保存
     * @param user
     * @return
     */
    @PostMapping
    @Log(method = "保存", resource = "用户信息")
    public Result save(@RequestBody User user) {
        String username = user.getUsername();
        if (StrUtil.isBlank(username)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        if (StrUtil.isBlank(user.getFullName())) {
            user.setFullName(username);
        }
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        return Result.success(userService.saveOrUpdate(user));
    }

    /**
     * 修改密码
     *
     * @param userPasswordDTO
     * @return
     */
    @PostMapping("/password")
    public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }

    @AuthAccess
    @PutMapping("/reset")
    public Result reset(@RequestBody UserPasswordDTO userPasswordDTO) {
        if (StrUtil.isBlank(userPasswordDTO.getUsername()) || StrUtil.isBlank(userPasswordDTO.getPhone())) {
            throw new ServiceException("-1", "参数异常");
        }
        User user1 = new User();
        user1.setUsername(userPasswordDTO.getUsername());
        user1.setPhone(userPasswordDTO.getPhone());
        List<User> list = userService.list(user1);
        if (CollUtil.isNotEmpty(list)) {
            User user = list.get(0);
            user.setPassword("123456");
            userService.saveOrUpdate(user);
        }
        return Result.success();
    }

    /**
    * 删除单条
    * @param id
    * @return
    */
    @DeleteMapping("/{id}")
    @Log(method = "删除", resource = "用户信息")
    public Result delete(@PathVariable Integer id) {
        userService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    @Log(method = "批量删除", resource = "用户信息")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        userService.removeByIds(ids);
        return Result.success();
    }

    /**
    * 查询所有
    * @return
    */
    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }

    /**
    * 根据id获取
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/username/{username}")
    public Result findByUsername(@PathVariable String username) {
        User userQuery = new User();
        userQuery.setUsername(username);
        List<User> users = userService.list(userQuery);
        User user = null;
        if (!CollectionUtils.isEmpty(users)) {
            user = users.get(0);
        }
        return Result.success(user);
    }

    /**
     * 分页数据
     * @param pageNum
     * @param pageSize
     * @param username
     * @param fullName
     * @return
     */
    @GetMapping("/page")
    @Log(method = "查询", resource = "用户信息")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String fullName) {
        User user = new User();
        user.setUsername(username);
        user.setFullName(fullName);
        return Result.success(new PageResult<>(userService.page(pageNum, pageSize, user)));
    }

}

