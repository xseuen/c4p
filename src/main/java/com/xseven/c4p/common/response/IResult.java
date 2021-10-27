package com.xseven.c4p.common.response;

public interface IResult {
    /**
     * @Author: xseven
     * @Description: 获取错误码
     */
    Integer getCode();
    /**
     * @Author: xseven
     * @Description: 获取错误信息
     */
    String getMessage();
}
