package com.example.accountservice.core.dtos.request.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateAccountRequest {
    @NotBlank(message = "This field is required")
    private String name;

    @NotBlank(message = "This field is required")
    private String accountNumber;

    @NotBlank(message = "This field is required")
    @Pattern(regexp = "^TR\\d{24}$", message = "IBAN must start with 'TR' and be followed by 24 digits")
    private String iban;

    @NotBlank(message = "This field is required")
    private String currency;

    @NotBlank(message = "This field is required")
    private double amount;
}
