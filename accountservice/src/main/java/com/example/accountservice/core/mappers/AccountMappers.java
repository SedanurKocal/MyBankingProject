package com.example.accountservice.core.mappers;

import com.example.accountservice.core.dtos.request.account.CreateAccountRequest;
import com.example.accountservice.core.dtos.response.account.CreateAccountResponse;
import com.example.accountservice.core.dtos.response.account.GetAllCustomerResponse;
import com.example.accountservice.core.dtos.response.account.GetByIdAccountResponse;
import com.example.accountservice.core.dtos.response.account.UpdateAmountResponse;
import com.example.accountservice.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMappers {
    AccountMappers INSTANCE = Mappers.getMapper(AccountMappers.class);
    Account requestToAccountMapper(CreateAccountRequest request);
    CreateAccountResponse accountToResponseMapper(Account account);
    @Mapping(source = "amount", target = "amount")
    GetByIdAccountResponse accountToGetByIdResponse(Account account);
    List<GetAllCustomerResponse> accountsToGetAllCustomerResponses(List<Account> accounts);
    UpdateAmountResponse updateAmountMapper(Account account);
}
