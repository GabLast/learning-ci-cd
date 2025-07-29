package com.showcase.application.springbootbackend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppInfo {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;
}
