package com.piggymetrics.experience.controller;

import com.piggymetrics.experience.domain.account.Account;
import com.piggymetrics.experience.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
//@RequestMapping("")
public class DefaultController {
    @GetMapping()
    public String welcome(){
        return "<h1>Welcome experience wtf</h1>";
    }

    @Autowired
    AccountService accountService;

    @GetMapping("/account/feign/all")
    public List<Account> getAccountsByFeign(){
        return accountService.getAccounts();
    }
    @GetMapping("/account")
    public String welcomeAccount(){
        return accountService.welcomeAccount();
    }
    @GetMapping("/account/token")
    public String getToken(){
        return accountService.getToken();
    }
}
