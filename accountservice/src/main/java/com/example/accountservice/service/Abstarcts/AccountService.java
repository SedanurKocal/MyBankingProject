package com.example.accountservice.service.Abstarcts;

import com.example.accountservice.core.dtos.request.account.CreateAccountRequest;
import com.example.accountservice.core.dtos.response.account.CreateAccountResponse;
import com.example.accountservice.core.dtos.response.account.GetAllCustomerResponse;
import com.example.accountservice.core.dtos.response.account.GetByIdAccountResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AccountService {
    CreateAccountResponse createAccount(int customerId, CreateAccountRequest request);
    GetByIdAccountResponse accountGetById(int accountId);
    List<GetAllCustomerResponse> getAllAccountsByCustomerId(Integer customerId);
    boolean doesAccountExist(Integer accountId);
    double getCurrentBalanceByAccountId(int AccountId);
    double updateAccountBalance(int accountId, double newBalance);
}
