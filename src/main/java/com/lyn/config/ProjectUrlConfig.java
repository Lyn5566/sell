package com.lyn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "projecturl")
@Data
public class ProjectUrlConfig {
    /**
     * 公众平台授权url
     */
    private String wxMpAuthorize;
    /**
     * 开放平台授权url
     */
    private String wxOpenAuthorize;
    /**
     * 项目里的url
     */
    private String sell;

}
