package com.example.neo4j.Service;

import com.example.neo4j.Model.Movie;
import com.example.neo4j.Model.Person;
import com.example.neo4j.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public Iterable<Movie> findByActor(Person actor) {
        return movieRepository.findByActor(actor);
    }

    public Optional<Movie> create(String title, long released) {
        Movie movie = new Movie(title, released);
        return movieRepository.save(movie);
    }

    public void delete(Movie movie) {
        movieRepository.deleteByTitle(movie.getTitle());
    }

    public void addActorToMovie(Person actor, Movie movie) {
        movieRepository.addActorToMovie(movie, actor);
    }

    public void deleteActorFromMovie(Person actor, Movie movie) {
        movieRepository.deleteActorFromMovie(movie, actor);
    }
}
