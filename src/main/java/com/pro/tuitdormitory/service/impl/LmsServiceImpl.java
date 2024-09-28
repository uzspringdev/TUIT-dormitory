package com.pro.tuitdormitory.service.impl;

import com.pro.tuitdormitory.domain.User;
import com.pro.tuitdormitory.dto.request.LoginRequest;
import com.pro.tuitdormitory.model.Gender;
import com.pro.tuitdormitory.model.Role;
import com.pro.tuitdormitory.service.LmsService;
import com.pro.tuitdormitory.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class LmsServiceImpl implements LmsService {
    private final RoleService roleService;
    private final BCryptPasswordEncoder encoder;
    private final RestTemplate restTemplate;

    @Override
    /*@Transactional*/
    public void authenticateLmsUser(LoginRequest loginRequest) {
    }


}
