package com.example.transactionService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="account_id")
    private Integer accountId;

    @Column(name="amount")
    private Double amount;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name="transaction_date")
    private LocalDateTime transactionDate;


}
