package com.piggymetrics.experience.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

@Configuration
public class OAuthFeignConfig {
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public OAuthFeignConfig(OAuth2AuthorizedClientManager authorizedClientManager, OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
        this.authorizedClientManager = authorizedClientManager;
    }
    @Bean
    public OAuth2FeignRequestInterceptor oAuth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(authorizedClientManager, oAuth2AuthorizedClientService);
    }
}
