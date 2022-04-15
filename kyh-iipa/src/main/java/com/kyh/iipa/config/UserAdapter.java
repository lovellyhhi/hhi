package com.kyh.iipa.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAdapter extends User {

    private UserInfo userInfo;
    
    
    public UserAdapter(UserInfo userInfo) {
        super(userInfo.getUsername(), userInfo.getPwd(), authorities(userInfo.getAuthGrp()));
        this.userInfo = userInfo;
    }

    private static Collection<? extends GrantedAuthority> authorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        roles.stream().forEach(r -> {
            log.debug("=============================================================================="+"ROLE_" + r);            
            authorities.add(new SimpleGrantedAuthority("ROLE_" + r));
        });
        return authorities;
    }
    
    public UserInfo getUserInfo() {
        return userInfo;
    }
    
}
