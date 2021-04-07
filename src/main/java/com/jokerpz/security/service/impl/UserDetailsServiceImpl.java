package com.jokerpz.security.service.impl;

import com.jokerpz.mapper.UserMapper;
import com.jokerpz.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.selectByUsername(username);
        List<String> roles = userMapper.selectAllRoleByUserId(user.getId());
        List<String> permissions = userMapper.selectPermissionsByUserId(user.getId());

        StringBuilder sb = new StringBuilder();

        for (String role : roles) {
            sb.append("ROLE_" + role + ",");
        }

        for (String permission : permissions) {
            sb.append(permission + ",");
        }

        String roleAndPermission = sb.toString().substring(0, sb.toString().length() - 1);

        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(roleAndPermission));
        return userDetails;
    }
}
