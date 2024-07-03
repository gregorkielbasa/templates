package com.migration.flyway;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlywayApplication implements ApplicationRunner {

	private final Repository repository;

    public FlywayApplication(Repository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
		SpringApplication.run(FlywayApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		repository.save(new Model("hhhh", 170, 50));
		repository.save(new Model("iii", 190, 80));

		repository.findAll()
				.forEach(System.out::println);

	}
}
