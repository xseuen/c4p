package com.xseven.c4p.sercurity;

import com.xseven.c4p.common.response.Result;
import com.xseven.c4p.common.response.ResultInfo;
import com.xseven.c4p.common.response.WriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName AuthenticationSuccessHandlerImpl
 * @Author: xseven
 * @Description 登录成功处理类
 * @Date 2022/3/3 15:37
 * @Version 1.0
 */
@Slf4j
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //登录成功后获取当前登录用户
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户[{}]于[{}]登录成功!", userDetail.getUser().getUsername(), new Date());
        WriteResponse.write(httpServletResponse, Result.success(ResultInfo.LOGIN_SUCCESS));
    }

}
