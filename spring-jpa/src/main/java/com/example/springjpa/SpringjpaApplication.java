package com.example.springjpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringjpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringjpaApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(PersonRepository repository) {
        return args -> {

            Cat cat1 = new Cat("Gato", 123);
            Cat cat2 = new Cat("Wato", 234);

            Person person = new Person();
            person.setName("John");
            person.setCats(List.of(cat1, cat2));

            repository.save(person);
            Person saved = repository.findById(person.getId()).orElseThrow(RuntimeException::new);
            System.out.println(saved);
        };
    }


}