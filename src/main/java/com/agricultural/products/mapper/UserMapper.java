package com.agricultural.products.mapper;

import com.agricultural.products.entity.dto.UserPasswordDTO;
import com.agricultural.products.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Collection;
import java.util.List;

/**
 *
 * 用户Mapper接口
 *
 */
@Mapper
public interface UserMapper {

    @Update("update sys_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);

    int insert(User user);

    int update(User user);

    List<User> selectList(@Param("user") User user);

    User getById(Integer id);

    int deleteBatchIds(@Param("ids") Collection<Integer> ids);

    int deleteById(Integer id);
}
