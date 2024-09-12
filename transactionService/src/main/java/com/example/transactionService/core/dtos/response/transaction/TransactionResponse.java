package com.example.transactionService.core.dtos.response.transaction;

import com.example.transactionService.entities.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private int id;
    private Double amount;
    private TransactionType type;
    private LocalDateTime transactionDate;
}
