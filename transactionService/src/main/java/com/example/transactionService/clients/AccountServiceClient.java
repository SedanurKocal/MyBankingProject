package com.example.transactionService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="accountservice")
public interface AccountServiceClient {
    @GetMapping("/api/v1/account/doesAccountExists/{accountId}")
    boolean doesAccountExist(@PathVariable Integer accountId);

    @GetMapping("/api/v1/account/getCurrentBalance/{accountId}")
    double getCurrentBalanceByAccountId(@PathVariable Integer accountId);

    @PutMapping("/api/v1/account/updateAccountBalance/{accountId}")
    double updateAccountBalance(@PathVariable Integer accountId, @RequestBody double newBalance);
}