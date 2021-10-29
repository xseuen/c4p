package com.xseven.c4p.common.config;

import cn.org.atool.fluent.mybatis.spring.MapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ApplicationConfig
 * @Author: xseven
 * @Description fluent_mybatis定义MapperFactory
 * @date 2021/10/26 15:08
 * @Version 1.0
 */
 @Configuration
public class ApplicationConfig {
    // @Bean("dataSource")
    // @ConfigurationProperties(prefix = "spring.datasource")
    // public DataSource newDataSource() {
    //     return DataSourceBuilder.create().build();
    // }
    //
    // @Bean
    // public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
    //     SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    //     bean.setDataSource(newDataSource());
    //     ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    //     // 以下部分根据自己的实际情况配置
    //     // 如果有mybatis原生文件, 请在这里加载
    //     bean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
    //     org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
    //     configuration.setLazyLoadingEnabled(true);
    //     configuration.setMapUnderscoreToCamelCase(true);
    //     bean.setConfiguration(configuration);
    //     return bean;
    // }
    //
    // 定义fluent mybatis的MapperFactory
    @Bean
    public MapperFactory mapperFactory() {
        return new MapperFactory();
    }
}


