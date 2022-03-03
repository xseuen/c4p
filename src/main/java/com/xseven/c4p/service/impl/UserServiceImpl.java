package com.xseven.c4p.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xseven.c4p.entity.User;
import com.xseven.c4p.mapper.UserMapper;
import com.xseven.c4p.sercurity.UserDetail;
import com.xseven.c4p.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xseven
 * @since 2021-11-17
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    final UserMapper userMapper;

    @Override
    public UserDetail getUserDetailsByUserName(String username) {
        UserDetail userDetail = new UserDetail();
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        User user = userMapper.selectOne(userQueryWrapper.eq(User::getUsername, username));
        userDetail.setUser(user);
        return userDetail;
    }
}
