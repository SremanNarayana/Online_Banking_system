package com.Online.Banking.account;

import com.Online.Banking.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface accountDAO extends JpaRepository<Account,Long> {
        List<Account> findByUser(User user);
        Account findByName(String accName);

}
