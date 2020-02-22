package com.lyn.utils;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成唯一主键
     */
    public static synchronized String getUniqueKey(){
        //Random random = new Random();
        // Integer num = random.nextInt(900000) + 100000;
        Integer num = (int)((Math.random()*9+1)*100000);
        return System.currentTimeMillis() + String.valueOf(num);
    }
}
