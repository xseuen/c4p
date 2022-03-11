package com.xseven.c4p;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@SpringBootTest
class C4pApplicationTests {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        //System.out.println(passwordEncoder.encode("123456"));
        String key = "aabb";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzV29yZCI6IjY2Njg4OSIsIm5iZiI6MTYzNTE1MDI3NiwiZXhwIjoxNjM1MTUwODc2LCJ1c2VyTmFtZSI6InpoYW5nc2FuIiwiaWF0IjoxNjM1MTUwMjc2fQ.Cq2AHyrZ-Q7U7O5BBPdEIBrm7aDtjQh4ZDvtIcLzQvg";
        JWT jwt = JWTUtil.parseToken(token);

        boolean verifyKey = jwt.setKey(key.getBytes()).verify();
        System.out.println(verifyKey);

        boolean verifyTime = jwt.validate(0);
        System.out.println(verifyTime);
    }

}
