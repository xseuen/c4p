package com.xseven.c4p.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xseven.c4p.common.constant.Constant;
import com.xseven.c4p.common.response.Result;
import com.xseven.c4p.common.response.ResultInfo;
import com.xseven.c4p.dto.UserDTO;
import com.xseven.c4p.entity.User;
import com.xseven.c4p.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xseven
 * @since 2021-11-17
 */
@RestController
//Lombok提供了一种通过构造器注入bean的方式,替换@Autowired,更简洁美观
@RequiredArgsConstructor
@RequestMapping("/users")
@Api(tags = "用户管理")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    final IUserService userService;
    //接口需要规范，请求路径不应该出现动词
    /**
     * 根据id获取用户
     * @param id 用户id
     * @return 用户信息或错误信息
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id获取用户")
    public Result getUserById(@PathVariable("id") Serializable id){
        try {
            User user = userService.getById(id);
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
     * @param page 分页参数
     * @return 用户信息或错误信息
     */
    @GetMapping("/")
    @ApiOperation(value = "分页获取所有用户",notes = "start是起始位置，pageSize是每页显示条数")
    public Result getUsers(Page page){
        try {
            Page<User> list = userService.page(page);
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
    public Result saveUser(@RequestBody UserDTO userDto){
        try {
            boolean res = userService.save(userDto);
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
    public Result updateUser(@RequestBody UserDTO userDto){
        if (userDto != null  && userDto.getId() != null){
            try {
                boolean res = userService.updateById(userDto);
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
    @DeleteMapping("/{id}")
    @ApiOperation("删除用户")
    public Result deleteUser(@PathVariable("id") Serializable id){
        try {
            boolean res = userService.removeById(id);
            return res? Result.ok(ResultInfo.SUCCESS): Result.error(ResultInfo.ERROR);
        }catch (Exception e) {
            logger.error(Constant.EXCEPTION_TITLE,e);
            return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 批量删除用户
     * @param idList 用户ID
     * @return 操作结果或错误信息
     */
    @DeleteMapping("/batch")
    @ApiOperation("批量删除用户")
    public Result batchDeleteUser(@RequestParam("idList[]") Collection<? extends Serializable> idList){
        try {
            boolean res = userService.removeByIds(idList);
            return res? Result.ok(ResultInfo.SUCCESS): Result.error(ResultInfo.ERROR);
        }catch (Exception e) {
            logger.error(Constant.EXCEPTION_TITLE,e);
            return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
        }
    }


}
