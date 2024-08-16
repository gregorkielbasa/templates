package com.kafka.timer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Timer;

@Component
public class AppRunner implements ApplicationRunner {

    private final BookReader bookReader;
    private final StatusReporter statusReporter;
    private final Timer timer = new Timer();

    public AppRunner(BookReader bookReader, StatusReporter statusReporter) {
        this.bookReader = bookReader;
        this.statusReporter = statusReporter;
    }

    @Override
    public void run(ApplicationArguments args) throws InterruptedException {
        startReporting();
        startReadingBook();
    }

    private void startReporting() {
        timer.scheduleAtFixedRate(statusReporter, 0, 1000); // Schedule task to run every 1000ms (1 second)
    }

    private void startReadingBook() throws InterruptedException {
        while (true) {
            Thread.sleep(TimerApplication.random.nextInt(950) + 50);

            bookReader.sendMessage();
        }
    }
}
