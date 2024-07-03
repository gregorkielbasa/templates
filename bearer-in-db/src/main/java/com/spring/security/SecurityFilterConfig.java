package com.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilterConfig {

    private final BearerTokenFilter bearerTokenFilter;

    public SecurityFilterConfig(BearerTokenFilter bearerTokenFilter) {
        this.bearerTokenFilter = bearerTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.addFilterBefore(bearerTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/admin/**").hasRole("ADMIN");
            requests.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN");
            requests.anyRequest().permitAll();
        });
        return http.build();
    }
}
