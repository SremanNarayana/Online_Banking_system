package com.Online.Banking.Transaction;

import com.Online.Banking.account.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")  // Specify the table name explicitly
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Explicit column name
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false, length = 20)
    private String type;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "from_account", length = 50)
    private String fromAccount;

    @Column(name = "to_account", length = 50)
    private String toAccount;

    @Column(name = "user_id")
    private Long userID;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)  // Ensure the foreign key is not nullable
    @JsonBackReference
    private Account account;

    public Transaction(LocalDateTime dateTime, String type, Double amount, Account account, String fromAccount, String toAccount, Long userID) {
        this.dateTime = dateTime;
        this.type = type;
        this.amount = amount;
        this.account = account;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.userID = userID;
    }
}
