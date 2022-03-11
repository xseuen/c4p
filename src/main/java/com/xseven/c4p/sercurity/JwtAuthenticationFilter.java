package com.xseven.c4p.sercurity;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.xseven.c4p.common.constant.Constant;
import com.xseven.c4p.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JWTAuthenticationFilter
 * @Author: xseven
 * @Description jwt认证
 * @Date 2022/3/8 16:27
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final IUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        //当前上下文中不存在认证信息
        //尝试获取token （token不一定存放在header中，比如也可以当做请求参数进行传递）
        //尝试从token中解析对象 （token中可以存放任何信息）
        //尝试从根据存放在token的信息去找对应的用户信息
        //用户找到用户信息信息 就在当前的认证上下文中进行设置,确保后续的filter能够检测到认证通过
        //if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String tokenStr = request.getHeader("token");
            // 验证token不为空
            if (StrUtil.isNotBlank(tokenStr)) {
                // 验证token有效性
                if (verifyToken(tokenStr)) {
                    JWT tokenObj = JWTUtil.parseToken(tokenStr);
                    if (Validator.isNotNull(tokenObj)) {
                        String username = tokenObj.getPayload(Constant.USERNAME).toString();
                        UserDetail user = userService.getUserDetailsByUserName(username);
                        //设置当前上下文的认证信息
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
                        authentication.setDetails(user);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }else {
                    SecurityContextHolder.clearContext();
                }
            }
        //}
        //调用下一个过滤器
        chain.doFilter(request, response);
    }

    private Boolean verifyToken(String token) {
        try {
            // 验证失败会抛出验证失败异常
            JWTValidator.of(token).validateAlgorithm(JWTSignerUtil.hs256(Constant.JWT_KEY.getBytes())).validateDate(DateUtil.date());
        } catch (ValidateException e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }
}
