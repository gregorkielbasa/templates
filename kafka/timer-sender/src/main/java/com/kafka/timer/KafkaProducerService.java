package com.kafka.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private final KafkaTemplate<Void, Long> longKafkaTemplate;
    private final KafkaTemplate<Long, String> stringKafkaTemplate;

    public KafkaProducerService(KafkaTemplate<Void, Long> longKafkaTemplate,
                                KafkaTemplate<Long, String> stringKafkaTemplate) {
        this.longKafkaTemplate = longKafkaTemplate;
        this.stringKafkaTemplate = stringKafkaTemplate;
    }

    public void sendLongMessage(Long number) {
        logger.info("New number: {}", number);
        longKafkaTemplate.send("current-line", number);
    }

    public void sendStringMessage(String text) {
        logger.info("New message: {}", text);
        long key = TimerApplication.sentLines;
        stringKafkaTemplate.send("book", key, text);
    }
}
