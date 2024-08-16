package com.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @KafkaListener(topics = {"current-line"})
    public void currentLineListen(Long message) {
        logger.info("Current line: {}", message);
    }

    @KafkaListener(topics = {"line-load"})
    public void lineLoadListen(Long message) {
        logger.info("Line/sec: {}", message);
    }
}
