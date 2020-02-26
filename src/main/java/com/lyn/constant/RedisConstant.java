package com.lyn.constant;

/**
 * redis常量
 */
public interface RedisConstant {
    //设置token前缀
    String TOKEN_PREFIX = "token_%s";
    //过期时间
    Integer EXPIRE = 7200;
}
