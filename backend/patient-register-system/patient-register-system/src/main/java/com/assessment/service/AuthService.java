package com.assessment.service;

import com.assessment.payload.request.CreateUserRequest;
import com.assessment.payload.request.LoginRequest;
import com.assessment.common.payload.JwtResponse;

public interface AuthService {
    void createUser(CreateUserRequest dto);
    JwtResponse login(LoginRequest dto);
}
