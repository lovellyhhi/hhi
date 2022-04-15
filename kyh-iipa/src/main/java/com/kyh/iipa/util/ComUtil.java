package com.kyh.iipa.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kyh.iipa.config.UserAdapter;
import com.kyh.iipa.config.UserInfo;

@Component
public class ComUtil {

    // ====================[  1) Servlet   ] ====================
    
    /*
     * Session 객체를 얻음
     */
    public static HttpSession getSession(boolean gen) {
        return getRequest().getSession(gen);
    }
    
    /*
     * Request 객체를 얻음
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }
    
    /*
     * Response 객체를 얻음
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getResponse();
    }

    /*
     * 인증 객체를 얻음
     */
    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }
    
    public static boolean isAnonymousUser() {
        if(getAuthentication() == null)return true; 
        Object principal = getAuthentication().getPrincipal();
        if(principal instanceof String && principal.equals("anonymousUser")){
            return true; 
        }
        return false;
    }
    
    /*
     * User 정보를 얻음
     */
    public static User getUser() {
        //로그인 정보 사용하는 로직을 배치로 돌릴때 null에러나기때문에 null인경우 null리턴
        if(getAuthentication() == null)return null; 
        Object principal = getAuthentication().getPrincipal();
        UserAdapter user = (UserAdapter) principal;
        return user;
    }
    
    /*
     * UserInfo 정보를 얻음
     */
    public static UserInfo getUserInfo() {
        UserAdapter user = (UserAdapter) getUser();
        //로그인 정보 사용하는 로직을 배치로 돌릴때 null에러나기때문에 null인경우 null리턴
        if(user==null)return null;
        return user.getUserInfo();
    }

    
 
}
