package com.kafka.streamer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
@EnableKafkaStreams
public class StreamerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamerApplication.class, args);
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("line-load")
				.partitions(1)
				.replicas(1)
				.build();
	}
}
