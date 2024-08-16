package com.kafka.timer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Component
public class BookReader {
    private final KafkaProducerService sender;
    private final Iterator<String> iterator;

    public BookReader(KafkaProducerService sender, @Value("${book.path}") String path) {
        this.sender = sender;
        iterator = loadText(path);
    }

    private Iterator<String> loadText(String path) {
        File file = new File(path);
        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine().trim();
                if (!nextLine.isBlank()) lines.add(nextLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines.iterator();
    }

    public String getNextLine() {
        return iterator.hasNext() ? iterator.next() : "the end";
    }

    public void sendMessage() {
        String message = getNextLine();

        sender.sendStringMessage(message);
        TimerApplication.sentLines++;
    }
}
