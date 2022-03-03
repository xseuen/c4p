package com.xseven.c4p.sercurity;

import com.xseven.c4p.common.factory.YamlPropertySourceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName CustomWebSecurityConfigure
 * @Author: xseven
 * @Description 安全认证
 * @Date 2022/3/3 15:04
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource(value = "classpath:security-config.yml", encoding = "UTF-8", factory = YamlPropertySourceFactory.class)
public class CustomWebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Value("${security.ignore.resource}")
    private String[] securityIgnoreResource;

    @Value("${security.ignore.api}")
    private String[] securityIgnoreApi;

    @Value("${security.login.url}")
    private String loginApi;

    @Value("${security.logout.url}")
    private String logoutApi;

    @Value("${security.login.username.key:username}")
    private String usernameKey;

    @Value("${security.login.password.key:password}")
    private String passwordKey;

    final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                // 对于静态资源的获取允许匿名访问
                .antMatchers(HttpMethod.GET, securityIgnoreResource).permitAll()
                // 对登录注册要允许匿名访问;
                .antMatchers(securityIgnoreApi).permitAll()
                // 其余请求全部需要登录后访问
                .anyRequest().authenticated()
                // 这里配置的loginProcessingUrl为页面中对应表单的 action ，该请求为 post，并设置可匿名访问
                .and().formLogin().loginProcessingUrl(loginApi).permitAll()
                // 这里指定的是表单中name="username"的参数作为登录用户名，name="password"的参数作为登录密码
                .usernameParameter(usernameKey).passwordParameter(passwordKey)
                // 登录成功后的返回结果
                .successHandler(new AuthenticationSuccessHandlerImpl())
                // 登录失败后的返回结果
                .failureHandler(new AuthenticationFailureHandlerImpl(usernameKey))
                // 这里配置的logoutUrl为登出接口，并设置可匿名访问
                .and().logout().logoutUrl(logoutApi).permitAll()
                // 登出后的返回结果
                .logoutSuccessHandler(new LogoutSuccessHandlerImpl())
                // 这里配置的为当未登录访问受保护资源时，返回json
                .and().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointHandler());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //配置密码加密，这里声明成bean，方便注册用户时直接注入
        return new BCryptPasswordEncoder();
    }

}
