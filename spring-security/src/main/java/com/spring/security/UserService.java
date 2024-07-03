package com.spring.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(String login, String password) {

         UserDetails newUser = User.withUsername(login)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();

         userDetailsManager.createUser(newUser);
    }

    public void deleteUser(String login) {
        userDetailsManager.deleteUser(login);
    }
}
