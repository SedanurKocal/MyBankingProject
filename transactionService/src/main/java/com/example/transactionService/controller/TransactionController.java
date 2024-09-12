package com.example.transactionService.controller;

import com.example.transactionService.core.dtos.request.transaction.DepositMoneyRequest;
import com.example.transactionService.core.dtos.request.transaction.TransferMoneyRequest;
import com.example.transactionService.core.dtos.request.transaction.WithdrawMoneyRequest;
import com.example.transactionService.core.dtos.response.transaction.DepositMoneyResponse;
import com.example.transactionService.core.dtos.response.transaction.TransactionResponse;
import com.example.transactionService.core.dtos.response.transaction.TransferMoneyResponse;
import com.example.transactionService.core.dtos.response.transaction.WithdrawMoneyResponse;
import com.example.transactionService.service.Abstarcts.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("depositMoney/{accountId}")
    public DepositMoneyResponse depositMoney(@PathVariable int accountId, @Valid @RequestBody DepositMoneyRequest request) {
        return transactionService.depositMoney(accountId, request);
    }

    @PostMapping("withdrawMoney/{accountId}")
    public WithdrawMoneyResponse withdrawMoney(@PathVariable int accountId, @Valid @RequestBody WithdrawMoneyRequest request) {
        return transactionService.withdrawMoney(accountId, request);
    }

    @GetMapping("getTransactions/{accountId}")
    public List<TransactionResponse> getTransactionsByAccountId(@PathVariable int accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }

    @PostMapping("transferMoney")
    public TransferMoneyResponse transferMoney(Integer fromAccountId, Integer toAccountId, @Valid @RequestBody TransferMoneyRequest request) {
        return transactionService.transferMoney(fromAccountId, toAccountId, request);
    }
}