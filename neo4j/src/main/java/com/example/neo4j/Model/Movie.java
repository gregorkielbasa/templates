package com.example.neo4j.Model;

public class Movie {
    private final String title;
    private final Long released;

    public Movie(String title, Long released) {
        this.title = title;
        this.released = released;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", released=" + released +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public Long getReleased() {
        return released;
    }
}
