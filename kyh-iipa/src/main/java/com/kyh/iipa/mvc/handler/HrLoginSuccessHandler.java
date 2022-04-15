package com.kyh.iipa.mvc.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.kyh.iipa.config.UserInfo;
import com.kyh.iipa.mvc.service.login.LoginService;
import com.kyh.iipa.util.ComUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <pre tip="HrLoginSuccessHandler 상세 설명">
 *  로그인 인증 후 메인페이지 가기 전 작업들을 정의할 수 있는 핸들러
 * </pre>
 * @history
 * <pre tip="변경사항기재"> 
 *      1) 2019.12.18 한혜인 : 신규생성
 * </pre>
 *
 */
@Slf4j
public class HrLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private LoginService loginService;

    
    /**
     * HrLoginSuccessHandler defaultUrl 파라미터를 받아 defaultTargetUrl을 설정
     * @param defaultTargetUrl
     */
    public HrLoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request , HttpServletResponse response ,
            Authentication authentication) throws ServletException , IOException {

//
//        log.debug("--------------------------------------------------------------------------------------------------------------------------------------");
//        log.debug(" IP : "+ getClientIp(request));
//        log.debug(" 선택한 언어 : " + request.getParameter("currentLanguage"));
//        log.debug("--------------------------------------------------------------------------------------------------------------------------------------");
//
        UserInfo userInfo = ComUtil.getUserInfo();
        
        HttpSession session = request.getSession();

        
        super.onAuthenticationSuccess(request, response, authentication);
        
    }

}
