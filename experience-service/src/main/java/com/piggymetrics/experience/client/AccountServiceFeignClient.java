package com.piggymetrics.experience.client;

import com.piggymetrics.experience.config.OAuthFeignConfig;
import com.piggymetrics.experience.domain.account.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "account-service", url = "${account.base-uri}", configuration = OAuthFeignConfig.class)
public interface AccountServiceFeignClient {
    @GetMapping(value = "/api/all")
    List<Account> getAccounts();
    @GetMapping("/api")
    String welcomeAccount();
    @GetMapping("/token")
    String getToken();
}
