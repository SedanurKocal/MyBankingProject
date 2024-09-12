package com.example.transactionService.service.Abstarcts;


import com.example.transactionService.core.dtos.request.transaction.DepositMoneyRequest;
import com.example.transactionService.core.dtos.request.transaction.TransferMoneyRequest;
import com.example.transactionService.core.dtos.request.transaction.WithdrawMoneyRequest;
import com.example.transactionService.core.dtos.response.transaction.DepositMoneyResponse;
import com.example.transactionService.core.dtos.response.transaction.TransactionResponse;
import com.example.transactionService.core.dtos.response.transaction.TransferMoneyResponse;
import com.example.transactionService.core.dtos.response.transaction.WithdrawMoneyResponse;

import java.util.List;


public interface TransactionService {
    DepositMoneyResponse depositMoney(Integer accountId, DepositMoneyRequest request);
    WithdrawMoneyResponse withdrawMoney(Integer accountId, WithdrawMoneyRequest request);
    List<TransactionResponse> getTransactionsByAccountId(Integer accountId);
    TransferMoneyResponse transferMoney(Integer fromAccountId, Integer toAccountId, TransferMoneyRequest request);
    }
