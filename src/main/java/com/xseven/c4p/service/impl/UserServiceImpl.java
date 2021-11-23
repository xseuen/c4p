package com.xseven.c4p.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xseven.c4p.entity.User;
import com.xseven.c4p.mapper.UserMapper;
import com.xseven.c4p.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    final UserMapper UserMapper;
}
