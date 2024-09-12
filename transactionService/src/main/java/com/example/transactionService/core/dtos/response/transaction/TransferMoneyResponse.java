package com.example.transactionService.core.dtos.response.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferMoneyResponse {
    private Double transferredAmount;
    private Integer fromTransactionId;
    private Integer toTransactionId;
}
