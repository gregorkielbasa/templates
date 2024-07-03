package com.example.graphqlserver;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public List<Book> allBooks() {
        return bookRepository.getAll();
    }

    @QueryMapping
    public Book bookById(@Argument String id) {
        return bookRepository.getById(id);
    }

    @QueryMapping
    public List<Book> bookByAuthor(@Argument String id) {
        return bookRepository.getByAuthorId(id);
    }

    @MutationMapping
    public Book createBook(@Argument String name, @Argument int pageCount, @Argument String authorId) {
        Book newBook = new Book(null, name, pageCount, authorId);
        return bookRepository.add(newBook);
    }

    @MutationMapping
    public boolean deleteBook(@Argument String id) {
        return bookRepository.remove(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return authorRepository.getById(book.authorId());
    }
}
