package com.kafka.timer;

import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Component
public class StatusReporter extends TimerTask {
    private final KafkaProducerService sender;

    public StatusReporter(KafkaProducerService sender) {
        this.sender = sender;
    }

    @Override
    public void run() {
        Long message = TimerApplication.sentLines;

        sender.sendLongMessage(message);
    }
}
