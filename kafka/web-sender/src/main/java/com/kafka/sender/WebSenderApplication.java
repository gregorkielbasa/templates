package com.kafka.sender;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class WebSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSenderApplication.class, args);
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("echo")
				.partitions(1)
				.replicas(1)
				.build();
	}
}
