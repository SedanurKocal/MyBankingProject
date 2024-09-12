package com.example.authService.service.Abstracts;

import com.example.authService.core.requests.LoginRequest;

public interface AuthService {
    String login( LoginRequest request);
}
