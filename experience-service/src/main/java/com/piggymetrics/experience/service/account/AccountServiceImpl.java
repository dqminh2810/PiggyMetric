package com.piggymetrics.experience.service.account;


import com.piggymetrics.experience.client.AccountServiceFeignClient;
import com.piggymetrics.experience.domain.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountServiceFeignClient accountServiceClient;

    public List<Account> getAccounts() {
        return accountServiceClient.getAccounts();
    }

    @Override
    public String welcomeAccount() {
        return accountServiceClient.welcomeAccount();
    }

    @Override
    public String getToken() {
        return accountServiceClient.getToken();
    }
}

