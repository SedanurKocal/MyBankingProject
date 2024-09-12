package com.example.accountservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="userService")
public interface UserServiceClient {
    @GetMapping("/api/v1/user/{customerId}/exists")
    boolean doesCustomerExist(@PathVariable Integer customerId);
}
