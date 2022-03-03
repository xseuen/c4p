package com.xseven.c4p.sercurity;

import com.xseven.c4p.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserDetailsServiceImpl
 * @Author: xseven
 * @Description security中连接数据库查询用户信息
 * @Date 2022/3/3 16:20
 * @Version 1.0
 */
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail userDetail = userService.getUserDetailsByUserName(username);
        if (userDetail.getUser() == null) {
            throw new UsernameNotFoundException("Not found username:" + username);
        }
        return userDetail;
    }

}
