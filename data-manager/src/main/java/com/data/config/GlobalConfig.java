package com.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@ConfigurationProperties(prefix = "global-config")
@Data
public class GlobalConfig {

    private String apiUrl;

    private String errUploadUrl;

    private String headUrl;

    private Map<String,String> gameConfig;
}
