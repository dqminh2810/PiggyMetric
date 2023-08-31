package com.piggymetrics.notification.service;

import com.piggymetrics.notification.client.AccountServiceFeignClient;
import com.piggymetrics.notification.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    AccountServiceFeignClient accountServiceClient;

    public List<Account> getAccounts() {
        return accountServiceClient.getAccounts();
    }
}
