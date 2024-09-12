package com.example.accountservice.core.dtos.response.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAmountResponse {
    private int id;
    private String name;
    private String accountNumber;
    private String currency;
    private double amount;
    private String iban;
}
