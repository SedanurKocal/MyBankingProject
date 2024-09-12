package com.example.authService.service.Concretes;

import com.example.authService.clients.UserServiceClient;
import com.example.authService.core.exeption.types.BusinessException;
import com.example.authService.core.requests.LoginRequest;
import com.example.authService.service.Abstracts.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserServiceClient userServiceClient;
    @Override
    public String login(LoginRequest request) {
        boolean result = userServiceClient.checkUser(request);
        if (result) {
            return "Login successful.";
        } else {
            throw new BusinessException("Your email or password is incorrect, please try again.");
        }
    }
}
