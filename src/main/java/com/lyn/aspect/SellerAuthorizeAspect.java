package com.lyn.aspect;

import com.lyn.constant.CookieConstant;
import com.lyn.constant.RedisConstant;
import com.lyn.exception.SellerAuthorizeException;
import com.lyn.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    private static final String POINT_CUT = "execution(public * com.lyn.controller.Seller*.*(..))" +
            "&&!execution(public * com.lyn.controller.SellerUserController.*(..))";
    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut(POINT_CUT)
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        //获取httpRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie == null){
           log.warn("【登录校验】cookie中查不到token");
           throw new SellerAuthorizeException();
        }
        //从redis中查询
        String redisValue = (String) redisTemplate.opsForValue().
                get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(redisValue)){
            log.warn("【登录校验】redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
