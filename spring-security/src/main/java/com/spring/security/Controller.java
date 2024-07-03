package com.spring.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private final UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/open")
    @ResponseStatus(code = HttpStatus.OK)
    public String open() {
        return "<h1>hello anyone</h1>";
    }

    @GetMapping("/user")
    @ResponseStatus(code = HttpStatus.OK)
    public String onlyForEmployers() {
        return "<h1>hello user or admin</h1>";
    }

    @GetMapping("/admin")
    @ResponseStatus(code = HttpStatus.OK)
    public String onlyForAdmin() {
        return "<h1>hello admin</h1>";
    }

    @PostMapping("/admin/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addUser(@RequestBody UserDto newUser) {
        userService.createUser(newUser.login(), newUser.password());
    }

    @PostMapping("/admin/{login}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable String login) {
        userService.deleteUser(login);
    }
}
