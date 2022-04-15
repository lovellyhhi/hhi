package com.kyh.iipa.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@RequiredArgsConstructor
public class PrgInterceptor implements AsyncHandlerInterceptor {

    //    @Override
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response ,
            Object handler) throws Exception {
        return true;
    }
}
