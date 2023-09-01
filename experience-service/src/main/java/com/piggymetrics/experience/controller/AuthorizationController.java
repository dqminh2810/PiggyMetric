package com.piggymetrics.experience.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Set;

@RestController
public class AuthorizationController {
    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    public AuthorizationController(OAuth2AuthorizedClientService oAuth2AuthorizedClientService){
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }
    @GetMapping(value = "/token")
    public String token(Authentication authentication) {
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient oAuth2AuthorizedClient = oAuth2AuthorizedClientService
                .loadAuthorizedClient(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(), oAuth2AuthenticationToken.getName());
        String jwtAccessTokenValue = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
        String jwtAccessTokenType = oAuth2AuthorizedClient.getAccessToken().getTokenType().getValue();
        String jwtAccessTokenExpiration = Objects.requireNonNull(oAuth2AuthorizedClient.getAccessToken().getExpiresAt()).toString();
        Set<String> jwtAccessTokenScopes = oAuth2AuthorizedClient.getAccessToken().getScopes();
        String jwtAccessTokenIssue = Objects.requireNonNull(oAuth2AuthorizedClient.getAccessToken().getIssuedAt()).toString();

        return "<h2> Access Token value </h2>" + jwtAccessTokenValue + "</br>"
                +"<h2> Access Token type </h2>" + jwtAccessTokenType + "</br>"
                +"<h2> Access Token expired at </h2>" + jwtAccessTokenExpiration + "</br>"
                +"<h2> Access Token issued at </h2>" + jwtAccessTokenIssue + "</br>"
                +"<h2> Scopes </h2>" + String.join(",", jwtAccessTokenScopes) + "</br>"
                ;
    }
}
