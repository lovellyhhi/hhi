package com.kyh.iipa.mvc.controller;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyh.iipa.config.AppUserInfo;
import com.kyh.iipa.config.UserInfo;
import com.kyh.iipa.util.ComUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PageController {

    private final ObjectMapper objectMapper;

    @PostMapping("/logout")
    public void loout(HttpServletRequest request , HttpServletResponse response) throws Exception {}

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String main(@AppUserInfo UserInfo userInfo , Model model ) {

        HttpServletRequest request = ComUtil.getRequest();
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //2021-08-03 localstorage 이용
        model.addAttribute("userInfo", userInfo);
        
        return "index";
    }
    

    @RequestMapping(value = "/inc/{uri}", method = {RequestMethod.GET, RequestMethod.POST})
    public String main(@AppUserInfo UserInfo userInfo , Model model, @PathVariable String uri ) {

        HttpServletRequest request = ComUtil.getRequest();
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //2021-08-03 localstorage 이용
        model.addAttribute("userInfo", userInfo);
        
        return "inc/"+uri;
    }
    
    
    
    
    
}
