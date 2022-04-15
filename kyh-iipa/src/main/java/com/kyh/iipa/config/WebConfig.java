package com.kyh.iipa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.kyh.iipa.mvc.interceptor.ApiInterceptor;
import com.kyh.iipa.mvc.interceptor.PrgInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer, WebMvcRegistrations {


    // RestController, Method 별 SEARCH, SAVE, PRINT 권한 체크
    @Autowired
    private ApiInterceptor apiInterceptor;

    // Controller 경로 접근권한 체크
    @Autowired
    private PrgInterceptor prgInterceptor;

    // @Autowired
    // private WebCacheInterceptor cacheInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(apiInterceptor).addPathPatterns("/api/**");

        registry.addInterceptor(prgInterceptor).addPathPatterns("/page/**");

        // registry.addInterceptor(cacheInterceptor)
        // .addPathPatterns("/cache/syncCache");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("login");

        // TODO Auto-generated method stub
        // WebMvcConfigurer.super.addViewControllers(registry);
    }


    @Bean
    public ClassLoaderTemplateResolver yourTemplateResolver() {

        ClassLoaderTemplateResolver configurer = new ClassLoaderTemplateResolver();
        configurer.setPrefix("templates/");
        configurer.setSuffix(".html");
        configurer.setTemplateMode(TemplateMode.HTML);
        configurer.setCharacterEncoding("UTF-8");
        configurer.setOrder(0); // this is important. This way spring //boot will listen to both places 0 and 1
        configurer.setCacheable(false);
        //configurer.setCheckExistence(true);
        return configurer;
    }



}
