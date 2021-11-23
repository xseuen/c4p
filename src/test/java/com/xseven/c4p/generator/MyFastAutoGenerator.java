package com.xseven.c4p.generator;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName CodeGenerator
 * @Author: xseven
 * @Description TODO
 * @date 2021/11/15 16:28
 * @Version 1.0
 */
@PropertySource(value={"classpath:generatorConfig.properties"})
public class MyFastAutoGenerator {
    /**
     * Project package
     */
    @Value("${generator.projectPackage}")
    private static String projectPackage;

    /**
     * Database url
     */
    @Value("${generator.jdbc.url}")
    private static String url;
    /**
     * Database username
     */
    @Value("${generator.jdbc.username}")
    private static String username;
    /**
     * Database password
     */
    @Value("${generator.jdbc.password}")
    private static String password;
    /**
     * author
     */
    @Value("${generator.author}")
    private static String author;
    /**
     * tablePrefix
     */
    @Value("${generator.strategy.tablePrefix}")
    private static String tablePrefix;


    /**
     * Init database information
     */
    static {
        Properties properties = new Properties();
        InputStream i = MyFastAutoGenerator.class.getResourceAsStream("/generatorConfig.properties");
        try {
            properties.load(i);
            url = properties.getProperty("generator.jdbc.url");
            username = properties.getProperty("generator.jdbc.username");
            password = properties.getProperty("generator.jdbc.password");
            author =  properties.getProperty("generator.author");
            tablePrefix =  properties.getProperty("generator.strategy.tablePrefix");
            projectPackage =  properties.getProperty("generator.projectPackage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            //.Builder("jdbc:mysql://localhost:3306/c4p?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", "root", "admin");
            .Builder(url, username, password);

    /**
     * 执行 run
     */
    public static void main(String[] args) throws SQLException {
        String projectPath = System.getProperty("user.dir");
        String javaPath = projectPackage.replaceAll("\\.", "/");
        com.baomidou.mybatisplus.generator.FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                // .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")).fileOverride())
                .globalConfig((scanner, builder) -> builder.author(author).fileOverride())
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(projectPackage))
                // 策略配置
                .strategyConfig(builder -> builder.addTablePrefix(tablePrefix)
                        //数据库表生成
                        .addInclude("user"))
                .templateEngine(new FreemarkerTemplateEngine())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .execute();
    }
}
