package com.example.springjpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Cat(@Column(name = "NAME", columnDefinition="VARCHAR(128)") String name, int size) {
    public Cat() {
        this("", 0);
    }
}