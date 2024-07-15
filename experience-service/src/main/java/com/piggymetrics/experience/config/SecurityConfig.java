package com.piggymetrics.experience.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class SecurityConfig {

    @Value("${gateway.base-uri}")
    private String gatewayUri;
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    String issuerUri;

    // Log incoming request
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/**")
                .authorizeHttpRequests((auth) ->
                {
                    auth.requestMatchers("/**").hasAuthority("SCOPE_experience.read");
                    auth.anyRequest().authenticated();
                })
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwt -> jwt.decoder(JwtDecoders.fromIssuerLocation(issuerUri))));
        return http.build();
    }
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
                new DefaultOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientRepository);

        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http, ClientRegistrationRepository clientRegistrationRepository) throws Exception {
//        http
//                .authorizeHttpRequests(authorize ->
//                        authorize
//                                .requestMatchers("/login/**", "/logout/**").permitAll()
////                                .requestMatchers(new AntPathRequestMatcher("/account/**")).permitAll()
//                                .anyRequest().authenticated()
//                )
//                .oauth2Login(
//                        oauth2Login ->
//                            oauth2Login.successHandler(redirectOauth2SuccessHandler())
////                        withDefaults()
//                )
//                .oauth2Client(withDefaults())
//                .logout(logout ->
//                        logout.logoutSuccessHandler(oidcLogoutSuccessHandler(clientRegistrationRepository)))
//
//        ;
//        return http.build();
//    }
//    private LogoutSuccessHandler oidcLogoutSuccessHandler(ClientRegistrationRepository clientRegistrationRepository) {
//        return new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
//    }
//
//    private SimpleUrlAuthenticationSuccessHandler redirectOauth2SuccessHandler(){
//        return new SimpleUrlAuthenticationSuccessHandler(gatewayUri);
//    }
}
