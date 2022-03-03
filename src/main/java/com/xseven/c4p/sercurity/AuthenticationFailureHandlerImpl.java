package com.xseven.c4p.sercurity;

import com.xseven.c4p.common.response.Result;
import com.xseven.c4p.common.response.ResultInfo;
import com.xseven.c4p.common.response.WriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName AuthenticationFailureHandlerImpl
 * @Author: xseven
 * @Description 登录失败处理类
 * @Date 2022/3/3 15:38
 * @Version 1.0
 */
@Slf4j
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    private String usernameKey;

    public AuthenticationFailureHandlerImpl(String usernameKey) {
        this.usernameKey = usernameKey;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultInfo resultInfo;

        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
            resultInfo = ResultInfo.USERNAME_OR_PASSWORD_ERROR;
        } else if (e instanceof LockedException) {
            resultInfo = ResultInfo.ACCOUNT_LOCKED_ERROR;
        } else if (e instanceof CredentialsExpiredException) {
            resultInfo = ResultInfo.CREDENTIALS_EXPIRED_ERROR;
        } else if (e instanceof AccountExpiredException) {
            resultInfo = ResultInfo.ACCOUNT_EXPIRED_ERROR;
        } else if (e instanceof DisabledException) {
            resultInfo = ResultInfo.ACCOUNT_DISABLED_ERROR;
        } else {
            resultInfo = ResultInfo.LOGIN_FAILED;
        }
        String username = httpServletRequest.getParameter(usernameKey);
        log.info("用户[{}]于[{}]登录失败,失败原因：[{}]", username, new Date(), resultInfo.getMessage());

        WriteResponse.write(httpServletResponse, Result.error(resultInfo));
    }

}
