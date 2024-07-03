package com.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfigInMemory {
//
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/user/**").hasRole("USER"));
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
//        http.httpBasic(withDefaults());
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//
//        UserDetails user1 = User.withUsername("user1")
//                .password("pass")
//                .roles("USER")
//                .build();
//
//        userDetailsManager.createUser(user1);
//        return userDetailsManager;
//    }
//}
