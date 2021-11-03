package com.xseven.c4p.service.impl;

import cn.org.atool.fluent.mybatis.model.StdPagedList;
import com.xseven.c4p.entity.UserEntity;
import com.xseven.c4p.mapper.UserMapper;
import com.xseven.c4p.service.UserService;
import com.xseven.c4p.wrapper.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @ClassName UserServiceImpl
 * @Author: xseven
 * @Description 用户服务类实现
 * @date 2021/10/28 9:20
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserMapper userMapper;
    @Override
    public UserEntity getUserById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public Boolean saveUser(UserEntity userEntity) {
        int res = userMapper.insertWithPk(userEntity);
        return res > 0;
    }

    @Override
    public Boolean updateUser(UserEntity userEntity) {
        return userMapper.updateById(userEntity) > 0;
    }

    @Override
    public StdPagedList<UserEntity> getUsers(Integer start, Integer pageSize) {
        StdPagedList<UserEntity> list = userMapper.stdPagedEntity(new UserQuery().where().isDeleted().isFalse().end().orderBy.gmtModified().desc().end().limit(start,pageSize));
        return list;
    }

    @Override
    public Boolean deleteUser(Long id) {
        int i = userMapper.logicDeleteById(id);
        return i > 0;
    }

}
