package com.example.graphql;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {

    private final List<Author> authors;

    public AuthorRepository() {
        this.authors = new ArrayList<>();

        add(new Author("author-1", "Joshua", "Bloch"));
        add(new Author("author-2", "Douglas", "Adams"));
        add(new Author("author-3", "Bill", "Bryson"));
    }

    public void add(Author author) {
        authors.add(author);
    }

    public Author getById(String id) {
        return authors.stream()
                .filter(author -> author.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Author> getAll() {
        return List.copyOf(authors);
    }
}
