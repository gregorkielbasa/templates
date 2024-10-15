package com.example.cicd;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class Controller {

    private final TimeService timeService;

    public Controller(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping
    public Model getCurrentTime() {
        return timeService.getCurrentTime();
    }

    @GetMapping("/{id}")
    public Model getTime(@PathVariable UUID id) {
        try {
            return timeService.getTime(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
