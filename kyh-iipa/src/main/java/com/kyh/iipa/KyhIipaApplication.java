package com.kyh.iipa;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableWebSecurity
@ComponentScan("com.kyh.iipa")
public class KyhIipaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyhIipaApplication.class, args);
	}
	

    @PostConstruct
    public void setTimezone(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

}
