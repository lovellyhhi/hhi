package com.kyh.iipa.mvc.filter;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiTimeoutFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest , ServletResponse servletResponse ,  FilterChain chain) throws IOException , ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (isAjaxRequest(request)) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }


    private boolean isAjaxRequest(HttpServletRequest request) {
        List<String> ajaxKey = Arrays.asList("ajaxLoad", "ajaxApi", "ajaxSheet");
        String ajaxValue = request.getHeader("AjaxKey");
        return ajaxValue != null
                && ajaxKey.contains(ajaxValue);

    }


}
