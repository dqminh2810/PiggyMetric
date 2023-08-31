package com.piggymetrics.notification.client;

import com.piggymetrics.notification.config.OAuthFeignConfig;
import com.piggymetrics.notification.domain.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "accounts-service", url = "http://localhost:8080", configuration = OAuthFeignConfig.class)
public interface AccountServiceFeignClient {
    @GetMapping(value = "/accounts")
    List<Account> getAccounts();
}
