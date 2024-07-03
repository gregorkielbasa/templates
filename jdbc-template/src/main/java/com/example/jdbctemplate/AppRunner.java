package com.example.jdbctemplate;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class AppRunner implements ApplicationRunner {
    private final OrderRepository repository;

    AppRunner(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println(repository.findOrderById(0));
        System.out.println(repository.findOrderById(1));
        System.out.println(repository.findOrderById(1000));
        System.out.println(repository.findOrderById(1001));

        Order order = new Order(100_000_000, List.of(
                new OrderItem(123, 10),
                new OrderItem(312, 20)));

        System.out.println(repository.save(order));
        System.out.println(repository.save(order));

        System.out.println(repository.findOrderById(0));
        System.out.println(repository.findOrderById(1));
        System.out.println(repository.findOrderById(1000));
        System.out.println(repository.findOrderById(1001));
        System.out.println(repository.findOrderById(1002));
    }
}
