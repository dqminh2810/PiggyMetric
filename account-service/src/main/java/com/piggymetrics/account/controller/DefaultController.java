package com.piggymetrics.account.controller;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class DefaultController {
    @Autowired
    private AccountService accountService;
    @GetMapping()
    public Mono<String> welcome(){ return Mono.just("<h1>Welcome account</h1>");}
    @GetMapping(path="/all")
    public Flux<Account> getAccounts(){return accountService.findAll();}
    @GetMapping(path="/{name}")
    public Mono<Account> getAccountByName(@PathVariable String name){return accountService.findByName(name);}
    @PostMapping()
    public Mono<Account> createNewAccount(@RequestBody User user){return accountService.create(user);}
    @PutMapping(path="/current/{name}")
    public void saveCurrentAccount(@PathVariable String name, @RequestBody Account account){accountService.saveChanges(name, account);}
}
