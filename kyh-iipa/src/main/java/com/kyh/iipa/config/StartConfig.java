package com.kyh.iipa.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartConfig implements ApplicationRunner {
    
    private final AppProp appProp;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        log.info("======== [ Young-Hwan Kim - IIPA Server Start  ] ==========");
        
        
        
        log.info("======== [ Successfully  Start On Server  ] ==========");
        
    }
}
