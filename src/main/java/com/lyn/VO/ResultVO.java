package com.lyn.VO;

import lombok.Data;

/**
 * http最外层请求
 */
@Data
public class ResultVO<T>{
    //错误码
    private Integer code;
    //错误信息
    private String msg;
    //具体内容
    private T data;
}
