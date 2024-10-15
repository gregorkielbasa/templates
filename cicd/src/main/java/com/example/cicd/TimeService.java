package com.example.cicd;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TimeService {

    private final TimeRepository repository;

    public TimeService(TimeRepository repository) {
        this.repository = repository;
    }

    public Model getCurrentTime() {
        Model newModel = new Model();
        repository.save(newModel);
        return newModel;
    }

    public Model getTime(UUID id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No such element has been found!"));
    }
}
