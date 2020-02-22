package com.lyn;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MysellApplicationTests {
    private final Logger logger =  LoggerFactory.getLogger(MysellApplicationTests.class);
    @Test
    void contextLoads() {
        logger.error("error...");
        logger.info("info...");
        logger.debug("debug...");
    }

}
