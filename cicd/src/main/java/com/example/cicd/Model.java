package com.example.cicd;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Model {

    @Id
    private final UUID id;
    private final LocalTime time;

    public Model() {
        this.id = UUID.randomUUID();
        this.time = LocalTime.now();
    }

    public UUID getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(id, model.id) && Objects.equals(time, model.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time);
    }
}
