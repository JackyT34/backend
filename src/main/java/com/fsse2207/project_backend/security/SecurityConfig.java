package com.fsse2207.project_backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable();

        http.oauth2ResourceServer(
                oauth2ResourceServer -> oauth2ResourceServer.jwt(
                        jwt -> jwt.decoder(JwtDecoders.fromIssuerLocation(issuer))
                )
        );
        return http.build();

    }
}

