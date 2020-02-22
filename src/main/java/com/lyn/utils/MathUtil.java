package com.lyn.utils;

public class MathUtil {

        private static final Double MONEY_RANGE = 0.01;

    public static Boolean equals(Double obj1,Double obj2){
        Double result = Math.abs(obj1 - obj2);
        if(result < MONEY_RANGE){
            return true;
        }else{
            return false;
        }
    }
}
