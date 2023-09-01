package com.piggymetrics.account.controller;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping(path="/all")
    public List<Account> getAccounts(){return accountService.findAll();}
    @GetMapping(path="/{name}")
    public Account getAccountByName(@PathVariable String name){return accountService.findByName(name);}
    @PostMapping(path="/")
    public Account createNewAccount(@RequestBody User user){return accountService.create(user);}
    @PutMapping(path="/current/{name}")
    public void saveCurrentAccount(@PathVariable String name, @RequestBody Account account){accountService.saveChanges(name, account);}
}
