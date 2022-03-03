package com.xseven.c4p.sercurity;

import com.xseven.c4p.common.response.Result;
import com.xseven.c4p.common.response.ResultInfo;
import com.xseven.c4p.common.response.WriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName LogoutSuccessHandlerImpl
 * @Author: xseven
 * @Description 登出成功处理类
 * @Date 2022/3/3 15:39
 * @Version 1.0
 */
@Slf4j
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if (authentication != null) {
            log.info("用户[{}]于[{}]注销成功!", ((UserDetail) authentication.getPrincipal()).getUsername(), new Date());
        }
        WriteResponse.write(httpServletResponse, Result.success(ResultInfo.LOGIN_SUCCESS));
    }

}
