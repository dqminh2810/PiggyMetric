package com.piggymetrics.notification.controller;

import com.piggymetrics.notification.domain.Account;
import com.piggymetrics.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping(path="/feign/accounts")
    public List<Account> getAccountsByFeign(){
        return notificationService.getAccounts();
    }
}
