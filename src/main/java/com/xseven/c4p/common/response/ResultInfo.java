package com.xseven.c4p.common.response;

/**
 * @ClassName ResultInfo
 * @Description 返回操作状态信息
 * @Author xseven
 * @DATE 2021/3/19 13:23
 * @Version 1.0
 */
public enum ResultInfo implements IResult {
    // 操作成功
    SUCCESS(200,"操作成功"),
    ERROR(400,"操作失败"),
    NOT_FOUND(404,"没有找到"),
    INTERNAL_SERVER_ERROR(500,"服务器处理时发生了意外"),
    UPDATE_ERROR(678,"更新失败"),
    UPDATE_SUCCESS(679,"修改成功"),
    NO_DATA_FOUND(999,"没有找到相关内容"),
    ACCESS_FORBIDDEN_ERROR(122,"访问禁止"),
    LOGIN_SUCCESS(123,"登录成功"),
    LOGOUT_SUCCESS(124,"登出成功"),
    LOGIN_FAILED(125,"登录失败"),
    USERNAME_OR_PASSWORD_ERROR(126,"用户名或者密码错误"),
    ACCOUNT_LOCKED_ERROR(127,"账户已被锁定"),
    CREDENTIALS_EXPIRED_ERROR(128,"登陆凭证过期"),
    ACCOUNT_EXPIRED_ERROR(129,"账户已过期"),
    ACCOUNT_DISABLED_ERROR(130,"账户未启用"),
    VERIFY_SUCCESS(756,"登录验证成功"),
    VERIFY_FAILED(885,"登录验证失败"),
    ;
    private final Integer code;
    private final String message;

    ResultInfo(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
