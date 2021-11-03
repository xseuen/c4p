package com.xseven.c4p.controller;

import cn.org.atool.fluent.mybatis.model.StdPagedList;
import com.sun.istack.internal.NotNull;
import com.xseven.c4p.common.constant.Constant;
import com.xseven.c4p.common.response.Result;
import com.xseven.c4p.common.response.ResultInfo;
import com.xseven.c4p.dto.UserDto;
import com.xseven.c4p.entity.UserEntity;
import com.xseven.c4p.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/users")
@Api(tags = "用户管理")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    final UserService userService;
    //接口需要规范，请求路径不应该出现动词
    /**
     * 根据id获取用户
     * @param id 用户id
     * @return 用户信息或错误信息
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id获取用户")
    public Result getUserById(@PathVariable("id")@NotNull Long id){
        try {
            UserEntity user = userService.getUserById(id);
            if (user == null){
                return Result.error(ResultInfo.NOT_FOUND);
            }
            return Result.ok().data(user);
        } catch (Exception e) {
            logger.error(Constant.EXCEPTION_TITLE,e);
            return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 分页获取所有用户
     * @return 用户信息或错误信息
     */
    @GetMapping("/")
    @ApiOperation(value = "分页获取所有用户",notes = "start是起始位置，pageSize是每页显示条数")
    public Result getUsers(Integer start, Integer pageSize){
        try {
            StdPagedList<UserEntity> list = userService.getUsers(start, pageSize);
            return Result.ok().data(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增用户
     * @param userDto 用户相关数据
     * @return 操作结果或错误信息
     */
    @PostMapping("/")
    @ApiOperation("新增用户")
    public Result saveUser(@RequestBody UserDto userDto){
        try {
            Boolean res = userService.saveUser(userDto.getUserEntity());
            return res? Result.ok(ResultInfo.SUCCESS): Result.error(ResultInfo.ERROR);
        }catch (Exception e) {
            logger.error(Constant.EXCEPTION_TITLE,e);
            return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 更新用户所有信息
     * @param userDto 用户相关数据
     * @return 操作结果或错误信息
     */
    @PutMapping("/")
    @ApiOperation("更新用户所有信息")
    public Result updateUser(@RequestBody UserDto userDto){
        if (userDto != null && userDto.getUserEntity() != null && userDto.getUserEntity().getId() != null){
            try {
                Boolean res = userService.updateUser(userDto.getUserEntity());
                return res? Result.ok(ResultInfo.SUCCESS): Result.error(ResultInfo.ERROR);
            }catch (Exception e) {
                logger.error(Constant.EXCEPTION_TITLE,e);
                return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
            }
        }
        return Result.error(ResultInfo.ERROR);
    }

    /**
     * 更新用户部分信息
     * @param userDto 用户相关数据
     * @return 操作结果或错误信息
     */
    @PatchMapping ("/")
    @ApiOperation("更新用户部分信息")
    public Result updatePartOfUser(@RequestBody UserDto userDto){
        if (userDto != null && userDto.getUserEntity() != null && userDto.getUserEntity().getId() != null){
            try {
                Boolean res = userService.updateUser(userDto.getUserEntity());
                return res? Result.ok(ResultInfo.SUCCESS): Result.error(ResultInfo.ERROR);
            }catch (Exception e) {
                logger.error(Constant.EXCEPTION_TITLE,e);
                return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
            }
        }
        return Result.error(ResultInfo.ERROR);
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @return 操作结果或错误信息
     */
    @DeleteMapping  ("/{id}")
    @ApiOperation("删除用户")
    public Result deleteUser(@PathVariable("id") @NotNull Long id){
        try {
            Boolean res = userService.deleteUser(id);
            return res? Result.ok(ResultInfo.SUCCESS): Result.error(ResultInfo.ERROR);
        }catch (Exception e) {
            logger.error(Constant.EXCEPTION_TITLE,e);
            return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
        }
    }



}
