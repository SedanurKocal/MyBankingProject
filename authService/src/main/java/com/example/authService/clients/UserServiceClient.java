package com.example.authService.clients;

import com.example.authService.core.requests.LoginRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="userService")
public interface UserServiceClient {
    @PostMapping("/api/v1/user/checkUser")
    boolean checkUser(@RequestBody LoginRequest request);
}
