package com.example.neo4j.Model;

public class Person {
    private final String name;
    private final Long born;

    public Person(String name, Long born) {
        this.name = name;
        this.born = born;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", born=" + born +
                '}';
    }

    public String getName() {
        return name;
    }

    public Long getBorn() {
        return born;
    }
}
