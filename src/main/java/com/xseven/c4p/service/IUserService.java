package com.xseven.c4p.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xseven.c4p.entity.User;
import com.xseven.c4p.sercurity.UserDetail;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xseven
 * @since 2021-11-17
 */
public interface IUserService extends IService<User> {
    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UserDetail getUserDetailsByUserName(String username);
}
