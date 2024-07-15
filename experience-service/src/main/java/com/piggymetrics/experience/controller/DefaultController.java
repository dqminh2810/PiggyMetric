package com.piggymetrics.experience.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController()
//@RequestMapping("")
public class DefaultController {
    @Autowired
    OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @GetMapping()
    public String welcome(){
        return "<h1>Welcome experience wtf</h1>";
    }

    @GetMapping(value = "/token")
    public String token(Authentication authentication) {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;

        String jwtAccessTokenValue = jwtAuthenticationToken.getToken().getTokenValue();
//        Map<String,  > jwtAccessTokenHeaders = jwtAuthenticationToken.getToken().getHeaders();
        String jwtAccessTokenName = jwtAuthenticationToken.getName();
//        String jwtAccessTokenType = jwtAuthenticationToken.get().getTokenType().getValue();
        String jwtAccessTokenExpiration = (jwtAuthenticationToken.getToken().getExpiresAt()).toString();
//        Set<String> jwtAccessTokenScopes = jwtAuthenticationToken.getToken().get
        String jwtAccessTokenIssue = Objects.requireNonNull(jwtAuthenticationToken.getToken().getIssuedAt()).toString();

        return "<h2> Access Token value </h2>" + jwtAccessTokenValue + "</br>"
                +"<h2> Access Token name </h2>" + jwtAccessTokenName + "</br>"
//                +"<h2> Access Token type </h2>" + jwtAccessTokenType + "</br>"
                +"<h2> Access Token expired at </h2>" + jwtAccessTokenExpiration + "</br>"
                +"<h2> Access Token issued at </h2>" + jwtAccessTokenIssue + "</br>"
//                +"<h2> Scopes </h2>" + String.join(",", jwtAccessTokenScopes) + "</br>"
                ;
    }
}
