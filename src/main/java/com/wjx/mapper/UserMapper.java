package com.wjx.mapper;

import com.wjx.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Gin
 * @description
 * @date 2023/8/30 11:28
 */
public interface UserMapper {
    public User selectUser(int id);

    public int insertUser(User user);

    @Select("select * from user")
    public List<User> selectAllUsers();
}