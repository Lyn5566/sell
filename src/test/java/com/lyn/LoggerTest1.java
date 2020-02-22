package com.lyn;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoggerTest1 {
    private final Logger logger =  LoggerFactory.getLogger(LoggerTest1.class);

    @Test
    public void test1(){
        String name = "java";
        String password = "123456";

        logger.debug("debug");
        logger.info("name:{},password:{}",name,password);
        logger.error("error...");
    }
}
