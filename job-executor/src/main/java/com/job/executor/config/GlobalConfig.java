package com.job.executor.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "global-config")
@Data
public class GlobalConfig {

    private String domain;

    private String onlinePlay;

}
