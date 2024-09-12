package com.example.accountservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="account_number")
    private String accountNumber;

    @Column(name="iban")
    private String iban;

    @Column(name = "amount")
    private Double amount;

    @Column(name="currency")
    private String currency;

    @Column(name="customer_id")
    private int customerId;

}
