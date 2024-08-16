package com.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @KafkaListener(topics = {"book", "echo"})
    public void textListen(String message) {
        logger.info("Received line: {}", message);
    }
}
