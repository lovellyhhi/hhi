package com.kyh.iipa.mvc.service.login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import com.kyh.iipa.config.AppProp;
import com.kyh.iipa.config.UserAdapter;
import com.kyh.iipa.config.UserInfo;
import com.kyh.iipa.util.ComUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Lazy
public class LoginService implements UserDetailsService {

    @Autowired
    private AppProp appProp;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) {
        

        HttpServletRequest request = ComUtil.getRequest();

        String password = (String) request.getParameter("password");
        String passEnc = passwordEncoder.encode(password);
        
        if(! ( username.equals(appProp.getUsername()) 
                && passwordEncoder.matches(appProp.getUserPw(), passEnc)
             )
        ){
            throw new UsernameNotFoundException(username);
        }
        
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        
        UserInfo userInfo = new UserInfo();
        userInfo.setEmpNm("김영환");
        userInfo.setUsername(username);
        //userInfo.setUserId(userId);
        userInfo.setPwd(passEnc);
        userInfo.setAuthGrp(roles);

        
        return new UserAdapter(userInfo);
    }
    
}
