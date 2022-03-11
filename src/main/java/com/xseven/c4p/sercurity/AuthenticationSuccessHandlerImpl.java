package com.xseven.c4p.sercurity;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.google.common.collect.Maps;
import com.xseven.c4p.common.constant.Constant;
import com.xseven.c4p.common.response.Result;
import com.xseven.c4p.common.response.ResultInfo;
import com.xseven.c4p.common.response.WriteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

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
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        //登录成功后获取当前登录用户
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String,Object> payload = Maps.newHashMap();
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, 2);
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷
        payload.put(Constant.USERNAME, userDetail.getUsername());
        String token = JWTUtil.createToken(payload, JWTSignerUtil.hs256(Constant.JWT_KEY.getBytes()));
        log.info("用户[{}]于[{}]登录成功!", userDetail.getUser().getUsername(), new Date());
        WriteResponse.write(httpServletResponse, Result.success(ResultInfo.LOGIN_SUCCESS).data(token));
    }

}
