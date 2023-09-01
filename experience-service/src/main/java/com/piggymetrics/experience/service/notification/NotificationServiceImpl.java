package com.piggymetrics.experience.service.notification;

import com.piggymetrics.experience.client.NotificationServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    NotificationServiceFeignClient notificationServiceFeignClient;
    @Override
    public String welcomeNotification() {
        return notificationServiceFeignClient.welcomeNotification();
    }
}
