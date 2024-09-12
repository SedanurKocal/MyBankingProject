package com.example.userService.service.concretes;

import com.example.userService.core.exeption.types.BusinessException;
import com.example.userService.service.abstracts.SmsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;

    @Value("${twilio.phone.number}")
    private String FROM_PHONE_NUMBER;

    private Map<String, String> verificationCodes = new HashMap<>();  // Telefon numarası ve kod saklama için map

    @PostConstruct
    public void initTwilio() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
    @Override
    public ResponseEntity<String> sendSms(String phoneNumber, String message) {
        String generatedMessage = generateVerificationMessage();
        Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(FROM_PHONE_NUMBER), generatedMessage).create();
        verificationCodes.put(phoneNumber, generatedMessage.split(": ")[1]);  // Telefon numarası ve kodu map'e ekliyoruz
        try {
            return ResponseEntity.ok("SMS başarıyla gönderildi.");
        } catch (Exception e) {
            e.printStackTrace();  // hatanın detayları için.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SMS gönderimi sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    private String generateVerificationMessage() {
        String verificationCode = String.format("%06d", new Random().nextInt(999999)); // 6 haneli kod
        return "Doğrulama kodun: " + verificationCode;
    }

    @Override
    public boolean verifyCode(String phoneNumber, String code) {
        String storedCode = verificationCodes.get(phoneNumber);  // Telefon numarası ile saklanan kodu alma işlemi.
        if (storedCode != null && storedCode.equals(code)) {
            verificationCodes.remove(phoneNumber);  // Kod doğrulandıktan sonra kaldırma işlemi.
            return true;
        } else {
            throw new BusinessException("Telfon numarsını ya da kodu hatalı girdiniz!");
        }
    }
}
