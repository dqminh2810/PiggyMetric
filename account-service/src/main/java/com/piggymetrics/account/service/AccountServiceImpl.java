package com.piggymetrics.account.service;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository repository;

    @Override
    public List<Account> findAll() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize((repository.findAll().iterator()), Spliterator.ORDERED), false).collect(Collectors.toList());
    }
    @Override
    public Account findByName(String accountName) {
        return null;
    }

    @Override
    public Account create(User user) {
        return null;
    }

    @Override
    public void saveChanges(String name, Account update) {

    }
}
