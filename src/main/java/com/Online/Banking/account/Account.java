package com.Online.Banking.account;

import com.Online.Banking.Transaction.Transaction;
import com.Online.Banking.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(nullable = false, length = 50)  // Adding column constraints
    private String type;
    @Column(nullable = false)
    private Double balance = 0.0;
    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference

    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transaction> transactions;

}
