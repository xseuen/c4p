package com.xseven.c4p.common.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.IDefaultSetter;

import java.util.function.Supplier;

/**
 * @ClassName MyCustomerInterface
 * @Author: xseven
 * @Description 配置逆向工程主键生成策略
 * @date 2021/10/26 0:14
 * @Version 1.0
 */
public interface MyCustomerInterface extends IDefaultSetter {
    @Override
    default Supplier<Object> pkGenerator(IEntity entity) {
        SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();
        return snowflakeGenerator::next;
    }
}
