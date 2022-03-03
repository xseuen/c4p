package com.xseven.c4p.sercurity;

import com.xseven.c4p.common.response.Result;
import com.xseven.c4p.common.response.ResultInfo;
import com.xseven.c4p.common.response.WriteResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AuthenticationEntryPointHandler
 * @Author: xseven
 * @Description 访问被禁止处理类
 * @Date 2022/3/3 15:41
 * @Version 1.0
 */
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        WriteResponse.write(httpServletResponse,  Result.error(ResultInfo.ACCESS_FORBIDDEN_ERROR));
    }

}
