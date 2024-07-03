package com.example.springjpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSONS")
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ElementCollection(targetClass = Cat.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "CATS", joinColumns = @JoinColumn(name = "PERSON_ID"))
    private List<Cat> cats = new ArrayList<>();

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cats=" + cats +
                '}';
    }
}