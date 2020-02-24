package com.lyn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WechatAccountConfig {
    /**
     * 公众平台使用的应用id
     */
    private String myAppId;
    /**
     * 公众平台使用的应用密钥
     */
    private String myAppSecret;
    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 支付完成后的异步通知地址
     */
    private String notifyUrl;
    /**
     * 开放平台id
     */
    private String OpenAppId;
    /**
     * 开放平台密钥
     */
    private String OpenAppSecret;
}
