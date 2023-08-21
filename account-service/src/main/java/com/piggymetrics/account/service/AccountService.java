package com.piggymetrics.account.service;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account findByName(String accountName);
    Account create(User user);
    void saveChanges(String name, Account update);
}
