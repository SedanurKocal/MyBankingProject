package com.example.userService.service.abstracts;

import org.springframework.http.ResponseEntity;

public interface SmsService {
    ResponseEntity<String> sendSms(String phoneNumber, String message);
    boolean verifyCode(String phoneNumber, String code);
}
