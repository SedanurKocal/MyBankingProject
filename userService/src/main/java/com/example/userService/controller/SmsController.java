package com.example.userService.controller;

import com.example.userService.service.abstracts.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sms")
@RequiredArgsConstructor
public class SmsController {
    @Autowired
    private final SmsService smsService;

    @PostMapping("/send-sms")
    public ResponseEntity<String> sendSms(@RequestParam String phoneNumber) {

        return smsService.sendSms(phoneNumber, null);
    }

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestParam String phoneNumber, @RequestParam String code) {
        boolean isVerified = smsService.verifyCode(phoneNumber, code);
        if (isVerified) {
            return ResponseEntity.ok("Kod başarıyla doğrulandı.");
        } else {
            return ResponseEntity.badRequest().body("Doğrulama kodu geçersiz veya telefon numarası hatalı.");
        }
    }

}
