package com.kafka.timer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Random;

@SpringBootApplication
public class TimerApplication {
	public static Random random = new Random();
	public static Long sentLines = 0L;

	public static void main(String[] args) {
		SpringApplication.run(TimerApplication.class, args);
	}

	@Bean
	public NewTopic stringTopic() {
		return TopicBuilder.name("book")
				.partitions(1)
				.replicas(1)
				.build();
	}

	@Bean
	public NewTopic longTopic() {
		return TopicBuilder.name("current-line")
				.partitions(1)
				.replicas(1)
				.build();
	}
}
