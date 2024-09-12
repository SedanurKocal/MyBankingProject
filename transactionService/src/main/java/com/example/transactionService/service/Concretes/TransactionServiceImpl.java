package com.example.transactionService.service.Concretes;

import com.example.transactionService.clients.AccountServiceClient;
import com.example.transactionService.core.dtos.request.transaction.DepositMoneyRequest;
import com.example.transactionService.core.dtos.request.transaction.TransferMoneyRequest;
import com.example.transactionService.core.dtos.request.transaction.WithdrawMoneyRequest;
import com.example.transactionService.core.dtos.response.transaction.DepositMoneyResponse;
import com.example.transactionService.core.dtos.response.transaction.TransactionResponse;
import com.example.transactionService.core.dtos.response.transaction.TransferMoneyResponse;
import com.example.transactionService.core.dtos.response.transaction.WithdrawMoneyResponse;
import com.example.transactionService.core.exception.types.BusinessException;
import com.example.transactionService.core.mappers.TransactionMappers;
import com.example.transactionService.entities.Transaction;
import com.example.transactionService.entities.TransactionType;
import com.example.transactionService.repositories.TransactionRepository;
import com.example.transactionService.service.Abstarcts.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final AccountServiceClient accountServiceClient;
    private final TransactionRepository repository;

    @Override
    public DepositMoneyResponse depositMoney(Integer accountId, DepositMoneyRequest request) {
        if (!accountServiceClient.doesAccountExist(accountId)) {
            throw new BusinessException("Hesap bulunamadı.");
        }

        double currentBalance = accountServiceClient.getCurrentBalanceByAccountId(accountId);

        double newBalance = currentBalance + request.getAmount().doubleValue();
        accountServiceClient.updateAccountBalance(accountId, newBalance);

        Transaction transaction = new Transaction();
        transaction.setAmount(newBalance);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAccountId(accountId);
        repository.save(transaction);
        DepositMoneyResponse response = TransactionMappers.INSTANCE.transactionToDepositMoneyResponse(transaction);
        return response;
    }

    @Override
    public WithdrawMoneyResponse withdrawMoney(Integer accountId, WithdrawMoneyRequest request) {
        if (!accountServiceClient.doesAccountExist(accountId)) {
            throw new BusinessException("Hesap bulunamadı.");
        }

        double currentBalance = accountServiceClient.getCurrentBalanceByAccountId(accountId);
        double withdrawalAmount = request.getAmount().doubleValue();

        if (currentBalance < withdrawalAmount) {
            throw new BusinessException("Yetersiz bakiye.");
        }

        double newBalance = currentBalance - withdrawalAmount;
        accountServiceClient.updateAccountBalance(accountId, newBalance);

        Transaction transaction = new Transaction();
        transaction.setAmount(-withdrawalAmount);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setType(TransactionType.WITHDRAWAL);
        transaction.setAccountId(accountId);
        repository.save(transaction);

        WithdrawMoneyResponse response = TransactionMappers.INSTANCE.transactionToWithdrawMoneyResponse(transaction);
        return response;
    }
    @Override
    public List<TransactionResponse> getTransactionsByAccountId(Integer accountId) {
        if (!accountServiceClient.doesAccountExist(accountId)) {
            throw new BusinessException("Hesap bulunamadı.");
        }

        List<Transaction> transactions = repository.findAllByAccountId(accountId);
        List<TransactionResponse> responses = TransactionMappers.INSTANCE.transactionToTransactionResponse(transactions);
        return responses;
    }

    @Override
    public TransferMoneyResponse transferMoney(Integer fromAccountId, Integer toAccountId, TransferMoneyRequest request) {
        if (!accountServiceClient.doesAccountExist(fromAccountId)) {
            throw new BusinessException("Gönderen hesap bulunamadı.");
        }

        if (!accountServiceClient.doesAccountExist(toAccountId)) {
            throw new BusinessException("Alıcı hesap bulunamadı.");
        }

        double fromAccountBalance = accountServiceClient.getCurrentBalanceByAccountId(fromAccountId);
        double transferAmount = request.getAmount().doubleValue();

        if (fromAccountBalance < transferAmount) {
            throw new BusinessException("Yetersiz bakiye.");
        }

        double newFromAccountBalance = fromAccountBalance - transferAmount;
        accountServiceClient.updateAccountBalance(fromAccountId, newFromAccountBalance);

        double toAccountBalance = accountServiceClient.getCurrentBalanceByAccountId(toAccountId);
        double newToAccountBalance = toAccountBalance + transferAmount;
        accountServiceClient.updateAccountBalance(toAccountId, newToAccountBalance);

        Transaction fromTransaction = new Transaction();
        fromTransaction.setAmount(transferAmount);
        fromTransaction.setTransactionDate(LocalDateTime.now());
        fromTransaction.setType(TransactionType.WITHDRAWAL);
        fromTransaction.setAccountId(fromAccountId);
        repository.save(fromTransaction);

        Transaction toTransaction = new Transaction();
        toTransaction.setAmount(transferAmount);
        toTransaction.setTransactionDate(LocalDateTime.now());
        toTransaction.setType(TransactionType.WITHDRAWAL);
        toTransaction.setAccountId(toAccountId);
        repository.save(toTransaction);

        TransferMoneyResponse response = new TransferMoneyResponse();
        response.setTransferredAmount(fromTransaction.getAmount());
        response.setFromTransactionId(fromTransaction.getId());
        response.setToTransactionId(toTransaction.getId());
        return response;
    }

}
