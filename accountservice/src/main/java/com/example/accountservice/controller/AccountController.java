package com.example.accountservice.controller;

import com.example.accountservice.clients.UserServiceClient;
import com.example.accountservice.core.dtos.request.account.CreateAccountRequest;
import com.example.accountservice.core.dtos.response.account.CreateAccountResponse;
import com.example.accountservice.core.dtos.response.account.GetAllCustomerResponse;
import com.example.accountservice.core.dtos.response.account.GetByIdAccountResponse;
import com.example.accountservice.service.Abstarcts.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountservice;

    @PutMapping("updateAccountBalance/{accountId}")
    public double updateAccountBalance(@PathVariable Integer accountId,@Valid @RequestBody double newBalance){
        return accountservice.updateAccountBalance(accountId, newBalance);
    }
    @GetMapping("getCurrentBalance/{accountId}")
    public double getCurrentBalanceByAccountId(@PathVariable Integer accountId){
        return accountservice.getCurrentBalanceByAccountId(accountId);
    }
    @GetMapping("doesAccountExists/{accountId}")
    public boolean doesAccountExist(@PathVariable Integer accountId){
        return accountservice.doesAccountExist(accountId);
    }

    @PostMapping("/createAccount/{customerId}")
    public CreateAccountResponse createAccount(@PathVariable Integer customerId, @Valid @RequestBody CreateAccountRequest request){
        return accountservice.createAccount(customerId, request);
    }

    @GetMapping("/{accountId}")
    public GetByIdAccountResponse AccountGetById(@PathVariable Integer accountId){
        return accountservice.accountGetById(accountId);
    }

    @GetMapping("/getAllAccounts/{customerId}")
    public List<GetAllCustomerResponse> GetAllAccountsByCustomerId(@PathVariable Integer customerId){
        return accountservice.getAllAccountsByCustomerId(customerId);
    }
}
