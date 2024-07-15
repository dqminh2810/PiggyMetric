package com.piggymetrics.account.service;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    Flux<Account> findAll();
    Mono<Account> findByName(String accountName);
    Mono<Account> create(User user);
    Mono<Void> saveChanges(String name, Account update);
}
