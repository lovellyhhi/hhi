package com.kyh.iipa.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kyh.iipa.config.AppProp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@RequiredArgsConstructor
public class ApiInterceptor  implements AsyncHandlerInterceptor {

	
	@Autowired
    private AppProp appProp;
    
    
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response ,
            Object handler) throws Exception {

        String reqPageId = request.getHeader("pageId");
        String reqUri       = request.getRequestURI();
            return true;
    }

    
    // post handle 로 client 로 return 하기 전 call,  
    // 현재는 여기서 insert  
    // TODO 배치서버로 호출
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("===============");
    }


}
