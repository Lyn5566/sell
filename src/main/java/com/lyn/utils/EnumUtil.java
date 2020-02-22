package com.lyn.utils;

import com.lyn.enums.CodeEnum;

public class EnumUtil {
    public static <T extends CodeEnum>T getByCode(Integer code, Class<T> clazz){
            for(T each : clazz.getEnumConstants()){
                if(each.getCode().equals(code)){
                    return each;
                }
            }
            return null;
    }
}
