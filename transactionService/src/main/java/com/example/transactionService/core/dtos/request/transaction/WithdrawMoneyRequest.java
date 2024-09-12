package com.example.transactionService.core.dtos.request.transaction;

import com.example.transactionService.entities.TransactionType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawMoneyRequest {
    @NotBlank(message = "This field is required")
    private Double amount;
}
