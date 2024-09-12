package com.example.transactionService.core.mappers;

import com.example.transactionService.core.dtos.response.transaction.DepositMoneyResponse;
import com.example.transactionService.core.dtos.response.transaction.TransactionResponse;
import com.example.transactionService.core.dtos.response.transaction.TransferMoneyResponse;
import com.example.transactionService.core.dtos.response.transaction.WithdrawMoneyResponse;
import com.example.transactionService.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMappers {
    TransactionMappers INSTANCE = Mappers.getMapper(TransactionMappers.class);
    DepositMoneyResponse transactionToDepositMoneyResponse(Transaction transaction);
    WithdrawMoneyResponse transactionToWithdrawMoneyResponse(Transaction transaction);
    List<TransactionResponse> transactionToTransactionResponse(List<Transaction> transactions);
}
