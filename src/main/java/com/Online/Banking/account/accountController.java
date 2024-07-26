package com.Online.Banking.account;

import com.Online.Banking.Transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class accountController {

    @Autowired
    private accountService AccountServ;

    @GetMapping("/")
    public String hello(){
        return "AWS beanStalk works perfect";
    }

    @PostMapping("/createAcc")
    public void createAcc(@RequestBody Account Acc, @RequestParam String username)
    {
         AccountServ.saveAcc(Acc,username);
    }

    @GetMapping("/getAllAcc")
    public List<Account> ListAcc(){
        return AccountServ.getAllAcc();
    }

    @GetMapping("/accounts")
    public List<Account> getAccountsByUsername(@RequestParam String username) {
        return AccountServ.getAccountsByUsername(username);
    }

//                                          Transact endpoints

// 1) Deposit endpoint

    @PostMapping("/deposit")
    public void deposit(@RequestParam Integer amount, @RequestParam String accName){
        AccountServ.depositFunc(amount.doubleValue(),accName);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestParam Integer amount, @RequestParam String accName){
        AccountServ.withdrawFunc(amount.doubleValue(),accName);
    }

    @PostMapping("/Transfer")
    public void transfer(@RequestParam String acc1, @RequestParam String acc2, @RequestParam Integer amount){
        AccountServ.transferFunc(acc1,acc2,amount);
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactionHistory(@RequestParam String email) {
        return AccountServ.getTransactionHistory(email);
    }

}


