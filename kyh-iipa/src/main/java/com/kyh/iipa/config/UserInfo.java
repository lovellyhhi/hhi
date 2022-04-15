package com.kyh.iipa.config;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -6184044926029805156L;

    private String username; //사용자ID
    
    private String pwd;

    private String empNm;

    private List<String> authGrp;


}
