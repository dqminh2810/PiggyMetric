package com.piggymetrics.account.controller;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping(path="/test")
    public String test(){
        return "test non protected resource";
    }
    @GetMapping(path="/accounts")
    public List<Account> getAccounts(){return accountService.findAll();}
    @GetMapping(path="/accounts/{name}")
    public Account getAccountByName(@PathVariable String name){return accountService.findByName(name);}
    @PostMapping(path="/accounts")
    public Account createNewAccount(@RequestBody User user){return accountService.create(user);}
    @PutMapping(path="/accounts/current/{name}")
    public void saveCurrentAccount(@PathVariable String name, @RequestBody Account account){accountService.saveChanges(name, account);}
}
