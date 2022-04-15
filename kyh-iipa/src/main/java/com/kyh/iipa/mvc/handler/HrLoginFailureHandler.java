package com.kyh.iipa.mvc.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.kyh.iipa.mvc.service.login.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HrLoginFailureHandler implements AuthenticationFailureHandler {

//    @Autowired
//    private SecurityMsgProp securityMsgProp;
    
    @Autowired
    private LoginService loginService;
    
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request , HttpServletResponse response ,
            AuthenticationException exception) throws IOException , ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/login"); 
        dispatcher.forward(request, response); 

    } 

}
