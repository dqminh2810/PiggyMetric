package com.piggymetrics.account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class ResourceServerConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    String issuerUri;
    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange(exchange -> {
                        //exchange.pathMatchers("/api/**").hasAuthority("SCOPE_account.read");
                        exchange.anyExchange().authenticated();
                }
                )
                .oauth2ResourceServer((oauth2) -> oauth2
                        //.jwt(jwt -> jwt.jwtDecoder(JwtDecoders.fromIssuerLocation(issuerUri)))
                        .jwt(Customizer.withDefaults())
                );

        /*
        http.securityMatcher("/api/**")
                .authorizeHttpRequests((auth) -> auth.requestMatchers("/api/**").hasAuthority("SCOPE_account.read"))
                .authorizeHttpRequests((auth) -> auth.anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwt -> jwt.decoder(JwtDecoders.fromIssuerLocation(issuerUri))));
         */
        return http.build();
    }
}
