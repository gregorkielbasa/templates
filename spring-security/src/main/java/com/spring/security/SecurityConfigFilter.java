package com.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfigFilter {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/admin/**").hasRole("ADMIN");
            requests.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN");
            requests.anyRequest().permitAll();
        });
        http.csrf((csrf) -> csrf.disable());
        http.httpBasic(withDefaults());
        return http.build();
    }
}
