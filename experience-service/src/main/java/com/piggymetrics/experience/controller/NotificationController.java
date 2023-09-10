package com.piggymetrics.experience.controller;

import com.piggymetrics.experience.domain.account.Account;
import com.piggymetrics.experience.service.account.AccountService;
import com.piggymetrics.experience.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping()
    public String welcomeNotification(){
        return notificationService.welcomeNotification();
    }

}
