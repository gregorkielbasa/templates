package org.example.aspect;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyClass {

    public void methodeOne() {
        System.out.println("MyClass.methodeOne");
    }

    public List<String> methodeTwo(List<String> input) {

        return input.stream()
                .filter( x -> x.contains("test"))
                .map(String::toUpperCase)
                .toList();
    }
}
