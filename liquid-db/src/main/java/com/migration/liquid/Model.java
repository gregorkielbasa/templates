package com.migration.liquid;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_KEY")
    @SequenceGenerator(name = "PRODUCT_KEY", initialValue = 100, allocationSize = 5)
    private int id;
    @NonNull
    private String name;
    private int weight;
    private int height;

    public Model() {
        this.id = 0;
        this.name = "";
        this.weight = 0;
        this.height = 0;
    }

    public Model(@NonNull String name, int weight, int height) {
        this.id = 0;
        this.name = name;
        this.weight = weight;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}
