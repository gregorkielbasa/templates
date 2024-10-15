package com.example.cicd;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

class ModelTest implements WithAssertions {

    private static Model model;
    private static Model model2;

    @BeforeAll
    static void init() {
        model = new Model();
        model2 = new Model();
    }

    @Test
    void getIdTest() {
        UUID result = model.getId();
        assertThat(result).isNotNull();
    }

    @Test
    void getIdCompareTest() {
        UUID result1 = model.getId();
        UUID result2 = model2.getId();
        assertThat(result1).isNotEqualTo(result2);
    }

    @Test
    void getTimeTest() {
        LocalTime result = model.getTime();
        assertThat(result).isCloseTo(LocalTime.now(), within(1, ChronoUnit.SECONDS));
    }

    @Test
    void getTimeCompareTest() {
        LocalTime result1 = model.getTime();
        LocalTime result2 = model.getTime();
        assertThat(result1).isCloseTo(result2, within(1, ChronoUnit.SECONDS));
    }

    @Test
    void equalsTest() {
        Boolean result = model.equals(model);
        assertThat(result).isTrue();
    }

    @Test
    void equalsComapreTest() {
        Boolean result = model.equals(model2);
        assertThat(result).isFalse();
    }
}