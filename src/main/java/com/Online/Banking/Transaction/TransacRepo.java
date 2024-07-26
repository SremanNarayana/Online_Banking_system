package com.Online.Banking.Transaction;

import com.Online.Banking.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountIn(List<Account> accounts);
}
