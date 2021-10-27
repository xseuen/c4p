package com.xseven.c4p.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Result
 * @Description 响应类
 * @Author xseven
 * @DATE 2021/3/18 21:13
 * @Version 1.0
 */
@Data
public class Result {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<>();

    /**
     * 构造方法私有化，里面的方法都是静态方法
     * 达到保护属性的作用
     */
    private  Result(){

    }

    /**
     * 这里是链式编程
     */
    public static Result ok(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultInfo.SUCCESS.getCode());
        result.setMessage(ResultInfo.SUCCESS.getMessage());
        return result;
    }
    public static Result error(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultInfo.ERROR.getCode());
        result.setMessage(ResultInfo.ERROR.getMessage());
        return result;
    }
    /**
     * @Author: xseven
     * @Description: 自定义返回成功与否
     */
    public  Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public  Result code(Integer code){
        this.setCode(code);
        return this;
    }
    public  Result message(String message){
        this.setMessage(message);
        return this;
    }
    public  Result data(String key, Object value){
        this.data.put(key,value);
        return this;
    }
    public  Result data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}
