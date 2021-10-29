package com.xseven.c4p.dto;

import com.xseven.c4p.entity.UserEntity;
import lombok.Data;

/**
 * @ClassName UserDto
 * @Author: xseven
 * @Description 用户数据传输类
 * @date 2021/10/27 17:08
 * @Version 1.0
 */
@Data
public class UserDto {
    private UserEntity userEntity;
}
