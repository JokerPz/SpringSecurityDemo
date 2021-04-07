package com.jokerpz.mapper;

import com.jokerpz.pojo.User;

import java.util.List;

public interface UserMapper {

    User selectByUsername(String username);

    List<String> selectAllRoleByUserId(Integer id);

    List<String> selectPermissionsByUserId(Integer userId);
}
