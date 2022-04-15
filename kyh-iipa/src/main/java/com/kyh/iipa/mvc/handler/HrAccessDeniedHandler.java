package com.kyh.iipa.mvc.handler;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.stereotype.Component;

import com.kyh.iipa.config.UserInfo;
import com.kyh.iipa.util.ComUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HrAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request , HttpServletResponse response ,
            AccessDeniedException accessDeniedException) throws IOException , ServletException {

        
        // csrf 오류
        if( accessDeniedException instanceof InvalidCsrfTokenException){
            
            log.debug("[ CSRF 에러 ] ");
            response.setCharacterEncoding("UTF-8");
            
            UserInfo userInfo = ComUtil.getUserInfo(); //userInfo 가 null 이 아니면 sendRedirect
            
            System.out.println("===========");
        }else{
            
            log.debug("~~~~~~~~~~~~~~~~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! --- HrAccessDeniedHandler");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"IO\":{\"Result\":-999,\"Message\":\"session timeout\"}}");
            
            response.sendRedirect("/error/error-403");
        }
                
        
    }

}
