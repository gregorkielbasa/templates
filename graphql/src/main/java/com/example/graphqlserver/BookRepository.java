package com.example.graphqlserver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private final List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();

        add(new Book(null, "Effective Java", 416, "author-1"));
        add(new Book(null, "Hitchhiker's Guide to the Galaxy", 208, "author-2"));
        add(new Book(null, "Down Under", 436, "author-3"));
    }

    public Book add(Book book) {
        String id = "book-" + (books.size() + 1);
        Book newBook = new Book(id, book.name(), book.pageCount(), book.authorId());
        books.add(newBook);
        System.out.println("new book created: " + book.name());
        return newBook;
    }

    public boolean remove(String id) {
        Book book = getById(id);
        if (book == null)
            return false;
        return books.remove(book);
    }

    public Book getById(String id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Book> getByAuthorId(String id) {
        return books.stream()
                .filter(book -> book.authorId().equals(id))
                .toList();
    }

    public List<Book> getAll() {
        return List.copyOf(books);
    }
}
