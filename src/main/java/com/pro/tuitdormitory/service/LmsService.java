package com.pro.tuitdormitory.service;

import com.pro.tuitdormitory.dto.request.LoginRequest;

public interface LmsService {

    void authenticateLmsUser(LoginRequest loginRequest);
}
