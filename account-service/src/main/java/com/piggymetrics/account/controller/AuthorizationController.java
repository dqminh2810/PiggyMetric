package com.piggymetrics.account.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Objects;

@RestController
public class AuthorizationController {
    @GetMapping(path = "/token")
    public String token(Authentication authentication) {
        System.out.println(authentication);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;

        String jwtAccessTokenValue = jwtAuthenticationToken.getToken().getTokenValue();
        String jwtAccessTokenName = jwtAuthenticationToken.getName();
        String jwtAccessTokenExpiration = (jwtAuthenticationToken.getToken().getExpiresAt()).toString();
        String jwtAccessTokenIssue = Objects.requireNonNull(jwtAuthenticationToken.getToken().getIssuedAt()).toString();

        return "<h2> Access Token value </h2>" + jwtAccessTokenValue + "</br>"
                +"<h2> Access Token name </h2>" + jwtAccessTokenName + "</br>"
                +"<h2> Access Token expired at </h2>" + jwtAccessTokenExpiration + "</br>"
                +"<h2> Access Token issued at </h2>" + jwtAccessTokenIssue + "</br>"
                ;
    }
//    @GetMapping(value = "/token")
//    public String token() {
//        System.out.println(SecurityContextHolder.getContext().getAuthentication());
//        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        OAuth2AuthorizedClient oAuth2AuthorizedClient = oAuth2AuthorizedClientService
//                .loadAuthorizedClient(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(), oAuth2AuthenticationToken.getName());
//        String jwtAccessTokenValue = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
//        String jwtAccessTokenType = oAuth2AuthorizedClient.getAccessToken().getTokenType().getValue();
//        String jwtAccessTokenExpiration = Objects.requireNonNull(oAuth2AuthorizedClient.getAccessToken().getExpiresAt()).toString();
//        Set<String> jwtAccessTokenScopes = oAuth2AuthorizedClient.getAccessToken().getScopes();
//        String jwtAccessTokenIssue = Objects.requireNonNull(oAuth2AuthorizedClient.getAccessToken().getIssuedAt()).toString();
//
//        return "<h2> Access Token value </h2>" + jwtAccessTokenValue + "</br>"
//                +"<h2> Access Token type </h2>" + jwtAccessTokenType + "</br>"
//                +"<h2> Access Token expired at </h2>" + jwtAccessTokenExpiration + "</br>"
//                +"<h2> Access Token issued at </h2>" + jwtAccessTokenIssue + "</br>"
//                +"<h2> Scopes </h2>" + String.join(",", jwtAccessTokenScopes) + "</br>"
//                ;
//    }
}
