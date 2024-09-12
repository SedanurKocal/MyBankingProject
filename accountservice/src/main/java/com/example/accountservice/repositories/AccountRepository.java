package com.example.accountservice.repositories;

import com.example.accountservice.entities.Account;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account,Integer> {
    List<Account> findAllByCustomerId(int customerId);
    Optional<Account> findByIban(String iban);

    Optional<Account> findByAccountNumber(String accountNumber);

   // @Query("SELECT a FROM Account a WHERE UPPER(a.iban) = UPPER(:iban)")
   // Optional<Account> findByIban(@Param("iban") String iban);
}
