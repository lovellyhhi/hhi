package com.kyh.iipa.mvc.handler;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
public class HrLogInOutHandlerConfig {

    @Bean
    public AuthenticationSuccessHandler successHandler() {
      return new HrLoginSuccessHandler("/");
    }
    
    
    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return new HrLoginFailureHandler();
    }
    
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new HrLogoutSuccessHandler();
    }
    
    
    // thymeleaf security
    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
    


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREAN);
        return localeResolver;

    }
    
}
