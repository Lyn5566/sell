package com.lyn.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatOpenConfig {
    @Autowired
    private WechatAccountConfig wechatAccountConfig;
    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOPenConfigStorage());
        return wxOpenService;
    }
    @Bean
    public WxMpConfigStorage wxOPenConfigStorage(){
        WxMpDefaultConfigImpl wxOPenConfigStorage = new WxMpDefaultConfigImpl();
        wxOPenConfigStorage.setAppId(wechatAccountConfig.getOpenAppId());
        wxOPenConfigStorage.setSecret(wechatAccountConfig.getOpenAppSecret());
        return wxOPenConfigStorage;
    }

}
