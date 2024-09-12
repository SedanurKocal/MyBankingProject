package com.example.accountservice.service.Concretes;

import com.example.accountservice.clients.UserServiceClient;
import com.example.accountservice.core.dtos.request.account.CreateAccountRequest;
import com.example.accountservice.core.dtos.response.account.CreateAccountResponse;
import com.example.accountservice.core.dtos.response.account.GetAllCustomerResponse;
import com.example.accountservice.core.dtos.response.account.GetByIdAccountResponse;
import com.example.accountservice.core.exception.types.BusinessException;
import com.example.accountservice.core.mappers.AccountMappers;
import com.example.accountservice.entities.Account;
import com.example.accountservice.repositories.AccountRepository;
import com.example.accountservice.service.Abstarcts.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserServiceClient userServiceClient;
    private final AccountRepository repository;
    @Override
    public CreateAccountResponse createAccount(int customerId, CreateAccountRequest request) {
        boolean customerExists = userServiceClient.doesCustomerExist(customerId);
        if (!customerExists) {
            throw new BusinessException("Customer does not exist");
        }

        Account account = AccountMappers.INSTANCE.requestToAccountMapper(request);
        account.setCustomerId(customerId);

        Account savedAccount = repository.save(account);

        CreateAccountResponse response = AccountMappers.INSTANCE.accountToResponseMapper(savedAccount);
        return response;
    }

    @Override
    public GetByIdAccountResponse accountGetById(int accountId) {
        Account account = repository.findById(accountId)
                .orElseThrow(() -> new BusinessException("Account does not exist"));

        GetByIdAccountResponse response = AccountMappers.INSTANCE.accountToGetByIdResponse(account);
        return response;
    }

    @Override
    public List<GetAllCustomerResponse> getAllAccountsByCustomerId(Integer customerId) {
        var customer = userServiceClient.doesCustomerExist(customerId);
        if(!customer){
            throw new BusinessException("Customer does not exist");
        }
        List<Account> accounts = repository.findAllByCustomerId(customerId);
        List<GetAllCustomerResponse> responses = AccountMappers.INSTANCE.accountsToGetAllCustomerResponses(accounts);
        return responses;
    }

    @Override
    public boolean doesAccountExist(Integer accountId) {
        Optional<Account> account = repository.findById(accountId);
        if (!account.isPresent()) {
            throw new BusinessException("Account does not exist");
        }
        return  true;
    }

    @Override
    public double getCurrentBalanceByAccountId(int accountId) {
        Account account = repository.findById(accountId)
                .orElseThrow(() -> new BusinessException("Account does not exist"));

        Double amount = account.getAmount();
        return amount != null ? amount : 0.0;
    }

    @Override
    public double updateAccountBalance(int accountId, double newBalance) {
        Account account = repository.findById(accountId)
                .orElseThrow(() -> new BusinessException("Account does not exist"));

        account.setAmount(newBalance);

        repository.save(account);
        return newBalance;
    }
}
