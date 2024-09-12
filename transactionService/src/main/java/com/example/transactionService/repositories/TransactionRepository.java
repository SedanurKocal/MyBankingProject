package com.example.transactionService.repositories;

import com.example.transactionService.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findAllByAccountId(int accountId);
}
