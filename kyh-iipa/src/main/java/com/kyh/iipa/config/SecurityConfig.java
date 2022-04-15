package com.kyh.iipa.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.kyh.iipa.mvc.filter.ApiTimeoutFilter;
import com.kyh.iipa.mvc.handler.HrAccessDeniedHandler;

import lombok.extern.slf4j.Slf4j;


/**
 * <pre tip="SecurityConfig 상세 설명">
 *  Spring Security를 사용한 Config Class
 * </pre>
 * @history
 * <pre tip="변경사항기재"> 
 *      1) 2019.12.18 이길환 : 신규생성
 * </pre>
 * 
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    
    @Autowired
    private AuthenticationFailureHandler loginFailureHandler;
    
//    @Autowired 
//    private RootAppConfigProperties rootAppConfigProperties;

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/assets/**", "/error");
        
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(characterEncodingFilter(),CsrfFilter.class);
        
//         http.addFilterAfter(new EhrFilter(), WebAsyncManagerIntegrationFilter.class);
         http.addFilterAfter(new ApiTimeoutFilter(), ExceptionTranslationFilter.class);
        

        http.authorizeRequests()
        .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                ;

        http.formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .successHandler(loginSuccessHandler)
            .failureHandler(loginFailureHandler)
            ;
        
        http.logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .logoutSuccessHandler(logoutSuccessHandler)
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
        ;
        // 로그아웃 후 브라우저의 뒤로가기 버튼을 사용하면 로그아웃이 성공하더라도 이전 페이지를 계속 볼 수 있음. 
        // 이는 브라우저가 페시를 캐시한다는 것 . 
        // headers() 구성 메소드로 보안 헤더를 활성화하면 브라우저가 페이지를 캐시하지 않도록 지시한다. 
        http.headers();
        
        http.exceptionHandling()
            .accessDeniedHandler(new HrAccessDeniedHandler());



        
        //------------------ CSRF 2020-10-07 hhi ----------------
        // CSRF is enabled by default, with Java Config
//      http.csrf().disable();
//      http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository());
        
        RequestMatcher csrfExceptMatcher = new RequestMatcher() {
            private AntPathRequestMatcher[] requestExceptMatchers = {
                    new AntPathRequestMatcher("/**/LoadExcel"),
                    new AntPathRequestMatcher("/**/LoadText"),
                    new AntPathRequestMatcher("/cache/syncCache"),
//                    new AntPathRequestMatcher("/test/**")
//                    new AntPathRequestMatcher("/**?SheetWork=")    // Query String 으로 들어온 값은 match 안됨 
//                    new AntPathRequestMatcher("/logout"),
                };
            
            @Override
            public boolean matches(HttpServletRequest request) {
                for (AntPathRequestMatcher rm : requestExceptMatchers) {
                    if (rm.matches(request)) return true; 
                }
                return false;
            }
        };
        
        http.csrf()
        .ignoringRequestMatchers(csrfExceptMatcher)
//        .ignoringAntMatchers("/**/Down2Excel")
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        
        http.headers().contentTypeOptions().disable();
        http.headers().frameOptions().sameOrigin();
    }
    

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;

    }

    
}

