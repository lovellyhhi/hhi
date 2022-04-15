package com.kyh.iipa.mvc.controller;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class ErrorPageHandler implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        
        log.error("=========== ErrorPageHandler.handleError call ===============");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
        log.info("httpStatus : "+httpStatus.toString());
        model.addAttribute("code", status.toString());
        model.addAttribute("msg", httpStatus.getReasonPhrase());
        model.addAttribute("timestamp", new Date());
        
       
        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "/error/404";
            }
            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "/error/401";
            }
        }
        
        return "/error/error";
    }

//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }

}
