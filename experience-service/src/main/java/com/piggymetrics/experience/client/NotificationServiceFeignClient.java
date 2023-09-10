package com.piggymetrics.experience.client;

import com.piggymetrics.experience.config.OAuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "notification-service", url = "${notification.base-uri}", configuration = OAuthFeignConfig.class)
public interface NotificationServiceFeignClient {
    @GetMapping()
    String welcomeNotification();
}
