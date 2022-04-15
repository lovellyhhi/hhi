package com.kyh.iipa.mvc.controller;


import java.lang.reflect.InvocationTargetException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kyh.iipa.config.AppProp;
import com.kyh.iipa.mvc.result.Result;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequiredArgsConstructor
public class RestApiController {

    private String prefixService = "Service";
    
    
    @Autowired
    AppProp appProp;
    
    @PostMapping("/api/{serviceName}/{methodName}")
    public Result searchData(@RequestBody(required = false) JSONObject jsonObj, @PathVariable String serviceName,@PathVariable String methodName ) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        return  new Result();
    }   
    

//    @GetMapping("/getSessionId")
//    public String getSessionId(HttpSession session) {
//        return session.getId();
//    }
    
    

}
