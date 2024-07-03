package com.example.graphqlserver;

public record Book (String id, String name, int pageCount, String authorId) {
}
