package com.xseven.c4p.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName CodeGenerator
 * @Author: xseven
 * @Description 代码逆向生成
 * @date 2021/11/15 16:33
 * @Version 1.0
 */
public class CodeGenerator {

    /**
     * Project package
     */
    private static String projectPackage;
    /**
     * Database url
     */
    private static String url;
    /**
     * Database username
     */
    private static String username;
    /**
     * Database password
     */
    private static String password;
    /**
     * author
     */
    private static String author;
    /**
     * tablePrefix
     */
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
            author =  System.getProperty("user.name");
            tablePrefix =  properties.getProperty("generator.strategy.tablePrefix");
            projectPackage =  properties.getProperty("generator.projectPackage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder(url, username, password)
            .build();

    /**
     * 策略配置
     */
    private StrategyConfig.Builder strategyConfig() {
        return new StrategyConfig.Builder()
                .addInclude("c4p_user")// 设置需要生成的表名
                .addTablePrefix(tablePrefix);
    }

    /**
     * 全局配置
     */
    private GlobalConfig.Builder globalConfig() {
        return new GlobalConfig.Builder().author(author);
    }

    /**
     * 包配置
     */
    private PackageConfig.Builder packageConfig() {
        return new PackageConfig.Builder().parent(projectPackage);
    }

    /**
     * 模板配置
     */
    private TemplateConfig.Builder templateConfig() {
        return new TemplateConfig.Builder();
    }


    /**
     * 代码生成
     *
     * @see OutputFile
     */
    @Test
    public void codeGenerator() {
        String projectPath = System.getProperty("user.dir");
        String javaPath = projectPackage.replaceAll("\\.", "/");
        // 设置自定义路径
        Map<OutputFile, String> pathInfo = new HashMap<>();
        pathInfo.put(OutputFile.mapperXml,projectPath + "/src/main/resources/mybatis/mapper/");
        pathInfo.put(OutputFile.entity, projectPath + "/src/main/java/" + javaPath + "/entity/");
        pathInfo.put(OutputFile.controller, projectPath + "/src/main/java/" + javaPath + "/controller/");
        pathInfo.put(OutputFile.service, projectPath + "/src/main/java/" + javaPath + "/service/");
        pathInfo.put(OutputFile.serviceImpl, projectPath + "/src/main/java/" + javaPath + "/service/impl/");
        pathInfo.put(OutputFile.mapper, projectPath + "/src/main/java/" + javaPath + "/mapper/");
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig()
                .entityBuilder()
                .enableLombok()
                .enableChainModel()
                .idType(IdType.ASSIGN_ID)
                .logicDeleteColumnName("is_deleted")
                .addTableFills(new Column("gmt_created", FieldFill.INSERT))    //基于数据库字段填充
                .addTableFills(new Column("gmt_modified", FieldFill.INSERT_UPDATE))
                .controllerBuilder()
                .enableRestStyle()
                .build());
        generator.packageInfo(packageConfig().pathInfo(pathInfo).build());
        generator.global(globalConfig().outputDir(projectPath + "/src/main/java")
                .enableSwagger()//开启swagger注解
                .disableOpenDir()//禁用生成代码后打开文件位置
                .build());
        generator.execute(new FreemarkerTemplateEngine());
    }

}
