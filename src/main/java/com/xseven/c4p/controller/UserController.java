package com.xseven.c4p.controller;

import com.sun.istack.internal.NotNull;
import com.xseven.c4p.common.response.Result;
import com.xseven.c4p.common.response.ResultInfo;
import com.xseven.c4p.entity.UserEntity;
import com.xseven.c4p.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Author: xseven
 * @Description 用户管理
 * @date 2021/10/26 14:05
 * @Version 1.0
 */
@RestController
//Lombok提供了一种通过构造器注入bean的方式,替换@Autowired,更简洁美观
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {
    final UserMapper userMapper;
    //接口需要规范，请求路径不应该出现动词
    /**
     * 根据id获取用户
     * @param id 用户id
     * @return 用户信息或错误信息
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id获取用户")
    public Result getUserById(@PathVariable("id")@NotNull Long id){
        UserEntity user = userMapper.findById(id);
        return user==null?Result.error().code(ResultInfo.NOT_FOUND.getCode()).message(ResultInfo.NOT_FOUND.getMessage()):Result.ok().data("user",user);
    }
}
