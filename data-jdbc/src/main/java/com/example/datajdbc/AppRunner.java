package com.example.datajdbc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
class AppRunner implements ApplicationRunner {
    private final OrderRepository repository;

    AppRunner(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        System.out.println(repository.findById(0L));
        System.out.println(repository.findById(1L));
        System.out.println(repository.findById(1000L));
        System.out.println(repository.findById(1001L));
        
        Order order = new Order(100_000_000, Set.of(
                new OrderItem( 123, 10),
                new OrderItem(312, 20)));

        System.out.println(repository.save(order));
        System.out.println(repository.save(order));

        System.out.println(repository.findById(0L));
        System.out.println(repository.findById(1L));
        System.out.println(repository.findById(1000L));
        System.out.println(repository.findById(1001L));
        System.out.println(repository.findById(1002L));
    }
}
