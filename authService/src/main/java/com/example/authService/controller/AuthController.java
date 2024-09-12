package com.example.authService.controller;

import com.example.authService.clients.UserServiceClient;
import com.example.authService.core.exeption.types.BusinessException;
import com.example.authService.core.requests.LoginRequest;
import com.example.authService.service.Abstracts.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("login")
    public String login(@Valid @RequestBody LoginRequest request) {
       return authService.login(request);
    }
}
