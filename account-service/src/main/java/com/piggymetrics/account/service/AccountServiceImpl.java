package com.piggymetrics.account.service;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Flux<Account> findAll() {
        //return StreamSupport.stream(Spliterators.spliteratorUnknownSize((repository.findAll().iterator()), Spliterator.ORDERED), false).collect(Collectors.toList());
        return repository.findAll();
    }
    @Override
    public Mono<Account> findByName(String accountName) {
        return null;
    }

    @Override
    public Mono<Account> create(User user) {
        return null;
    }

    @Override
    public Mono<Void> saveChanges(String name, Account update) {
        return null;
    }
}
