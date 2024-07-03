package com.spring.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/open")
    @ResponseStatus(code = HttpStatus.OK)
    public String open() {
        return "<h1>Hello anyone</h1>";
    }

    @GetMapping("/user")
    @ResponseStatus(code = HttpStatus.OK)
    public String onlyForEmployers() {
        return "<h1>Hello user or admin</h1>";
    }

    @GetMapping("/admin")
    @ResponseStatus(code = HttpStatus.OK)
    public String onlyForAdmin() {
        return "<h1>Hello admin</h1>";
    }
}

