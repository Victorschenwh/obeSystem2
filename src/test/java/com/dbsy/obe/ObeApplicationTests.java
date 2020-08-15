package com.dbsy.obe;

import com.dbsy.obe.annotation.Authority;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class ObeApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    void contextLoads() {
        long i = redisTemplate.opsForValue().increment("i", 10);
        log.info(i + "");
    }

}
