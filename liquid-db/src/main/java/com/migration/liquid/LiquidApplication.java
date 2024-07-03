package com.migration.liquid;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiquidApplication implements ApplicationRunner {

	private final Repository repository;

	public LiquidApplication(Repository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiquidApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		repository.save(new Model("ggg", 55, 120));
		repository.save(new Model("hhh", 65, 150));

		repository.findAll()
				.forEach(System.out::println);

	}
}