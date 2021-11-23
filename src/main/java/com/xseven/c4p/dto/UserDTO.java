package com.xseven.c4p.dto;

import com.xseven.c4p.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName UserDTO
 * @Author: xseven
 * @Description User数据传输对象
 * @date 2021/11/17 11:45
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "User数据传输对象")
public class UserDTO extends User{
}
