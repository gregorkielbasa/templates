package com.spring.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    public String login(@RequestBody UserDto userDto) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.login(), userDto.password()));
            return BearerTokenProvider.generateToken(userDto.login());
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
