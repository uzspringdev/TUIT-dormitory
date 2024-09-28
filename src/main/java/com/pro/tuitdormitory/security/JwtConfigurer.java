package com.pro.tuitdormitory.security;

import com.pro.tuitdormitory.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtConfigurer implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {
    //private final TokenService tokenService;
    private final JwtUtils jwtUtils;


    @Override
    public void init(HttpSecurity builder) throws Exception {

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        TokenSecurityFilter tokenSecurityFilter = new TokenSecurityFilter(jwtUtils);
        http
                .addFilterBefore(tokenSecurityFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
