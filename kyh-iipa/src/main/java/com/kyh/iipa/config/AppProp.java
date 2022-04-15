package com.kyh.iipa.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Component
@ConfigurationProperties("kyh.iipa")
public class AppProp {
    private String userPw;
    private String username;
}
