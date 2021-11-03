package com.xseven.c4p.service;

import cn.org.atool.fluent.mybatis.model.StdPagedList;
import com.xseven.c4p.entity.UserEntity;

/**
 * @ClassName UserService
 * @Author: xseven
 * @Description 用户服务类接口
 * @date 2021/10/28 9:19
 * @Version 1.0
 */
public interface UserService {
    UserEntity getUserById(Long id);

    Boolean saveUser(UserEntity userEntity);

    Boolean updateUser(UserEntity userEntity);

    StdPagedList<UserEntity> getUsers(Integer start, Integer pageSize);

    Boolean deleteUser(Long id);
}
