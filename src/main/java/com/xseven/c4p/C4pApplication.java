package com.xseven.c4p;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.xseven.c4p.mapper"})
public class C4pApplication {

    public static void main(String[] args) {
        SpringApplication.run(C4pApplication.class, args);
    }

}
