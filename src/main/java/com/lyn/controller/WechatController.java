package com.lyn.controller;

import com.lyn.config.ProjectUrlConfig;
import com.lyn.config.WechatMpConfig;
import com.lyn.enums.ResultEnum;
import com.lyn.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 微信授权
 * 获取openid
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WxMpService wxOpenService;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    /*请求code*/
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
        //1.配置
        //2.调用方法
        String url=projectUrlConfig.getWxMpAuthorize()+ "/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url,
                WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl,"UTF-8"));
        log.info("【微信网页授权】获取code result={}", redirectUrl);
        return "redirect:" + redirectUrl;
    }
    /*通过code参数加上AppID和AppSecret等，通过API换取access_token*/
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code
            ,@RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            log.info("【微信网页授权】code={}",code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openid = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openid;
    }
    /**
     * 开放平台
     */
    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
        String url = projectUrlConfig.getWxOpenAuthorize() + "/sell/wechat/qrUserInfo";
        String redirectUrl = wxOpenService.buildQrConnectUrl(url,
                WxConsts.QrConnectScope.SNSAPI_LOGIN,URLEncoder.encode(returnUrl,"UTF-8"));
        log.info("【微信网页授权】获取code result={}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
             wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}",e);
            throw new SellException(ResultEnum.WECHAT_OPEN_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:?openid:"+openId;
    }

}
