package com.Online.Banking.account;

import com.Online.Banking.Transaction.TransacRepo;
import com.Online.Banking.Transaction.Transaction;
import com.Online.Banking.User.User;
import com.Online.Banking.User.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class accountService {
    @Autowired
    private accountDAO accountRepo;

    @Autowired
    private userRepo repo;

    @Autowired
    private TransacRepo transacRepo;



    public void saveAcc(Account acc, String email) {
        User user = repo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (user != null) {
            acc.setUser(user);
            accountRepo.save(acc);
        } else {
            throw new RuntimeException("User not found");
        }
    }
    public List<Account> getAllAcc(){
        return accountRepo.findAll();
    }

    public List<Account> getAccountsByUsername(String email) {
        User user = repo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        if (user != null) {
            return accountRepo.findByUser(user);
        } else {
            return Collections.emptyList();
        }
    }

    public void depositFunc(double amount, String name){
        Account acc1 = accountRepo.findByName(name);
        if (acc1 == null) {
            throw new RuntimeException("Account not found");
        }
        acc1.setBalance(acc1.getBalance() + amount);
        accountRepo.save(acc1);
    }

    public void withdrawFunc(double amount, String name){
        Account acc1 = accountRepo.findByName(name);
        if (acc1 == null) {
            throw new RuntimeException("Account not found");
        }
        acc1.setBalance(acc1.getBalance() - amount);
        accountRepo.save(acc1);
    }

    public void transferFunc(String acc1, String acc2, Integer amt) {
        Account accountFrom = accountRepo.findByName(acc1);
        Account accountTo = accountRepo.findByName(acc2);

        if (accountFrom == null || accountTo == null) {
            throw new IllegalArgumentException("One or both accounts do not exist.");
        }

        if (accountFrom.getBalance() < amt) {
            throw new IllegalArgumentException("Insufficient balance in account ");
        }

        accountFrom.setBalance(accountFrom.getBalance() - amt);
        accountTo.setBalance(accountTo.getBalance() + amt);

        // Create a single transaction record for the transfer
        Transaction transaction = new Transaction(
                LocalDateTime.now(),
                "Transfer",
                amt.doubleValue(),
                accountFrom,
                accountFrom.getName(),
                accountTo.getName(),
                accountTo.getUserID()
        );

        transacRepo.save(transaction);

        accountRepo.save(accountFrom);
        accountRepo.save(accountTo);
    }

    public List<Transaction> getTransactionHistory(String email) {
        User user = repo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        List<Account> accounts = accountRepo.findByUser(user);
        return transacRepo.findByAccountIn(accounts);
    }



}
