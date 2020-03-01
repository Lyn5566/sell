package com.lyn.handler;

import com.lyn.config.ProjectUrlConfig;
import com.lyn.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {
    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    //要跳转的url
    //http://wxsells.nat100.top/sell/wechat/qrAuthorize?returnUrl=http://wxsells.nat100.top/sell/seller/login

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWxOpenAuthorize()
                .concat("/sell/wechat/qrAuthorize?"))
                .concat("returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }
}
