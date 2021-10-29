package com.xseven.c4p.common.config;

/**
 * @ProjectName: c4p-demo
 * @Package: com.xseven.c4p.common.config
 * @ClassName: CrosCofig
 * @Author: GLO
 * @Description: 跨域配置
 * @Date: 2021/10/27 15:47
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","HEAD")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    /**
     * 配置放行的资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //"/user/file/avatar/**"请求的路径，"/CoolCatFile/Avatar/"映射的本地路径
        //registry.addResourceHandler("/user/file/avatar/**").addResourceLocations("file:D:" + "/CoolCatFile/Avatar/");
        //  请求路径的格式‘http://localhost:8080/path/+’
        //  addResourceLocations 添加需要访问文件的本地地址
        registry.addResourceHandler("/path/**")
                .addResourceLocations("file:D:" + "/suse/hotpot-demo/src/main/resources/static/img/");
    }
}
