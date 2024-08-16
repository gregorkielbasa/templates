package com.kafka.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final KafkaTemplate<Long, String> kafkaTemplate;

    public Controller(KafkaTemplate<Long, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/{message}")
    public void send(@PathVariable String message) {

        logger.info("New message: {}", message);
        kafkaTemplate.send("echo", message);
    }
}
