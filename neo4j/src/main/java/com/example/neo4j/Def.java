package com.example.neo4j;

import com.example.neo4j.Model.Movie;
import com.example.neo4j.Model.Person;
import com.example.neo4j.Service.ActorService;
import com.example.neo4j.Service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class Def implements CommandLineRunner {

    private final ActorService actorService;
    private final MovieService movieService;

    public Def(ActorService actorService, MovieService movieService) {
        this.actorService = actorService;
        this.movieService = movieService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. Show all actors
                    2. Find actor by name
                    3. Show all movies
                    4. Find movie by title
                    5. Add an actor
                    6. Add a movie
                    7. Delete an actor
                    8. Delete a movie
                    11. Find movies by actor name
                    12. Find actors by movie title
                    13. Add actor to a movie
                    14. Delete actor from a movie
                    """);


            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1 -> actorService.findAll().forEach(System.out::println);
                case 2 -> System.out.println(actorService.findByName(scanner.nextLine()));
                case 3 -> movieService.findAll().forEach(System.out::println);
                case 4 -> System.out.println(movieService.findByTitle(scanner.nextLine()));
                case 5 -> addActor();
                case 6 -> addMovie();
                case 7 -> deleteActor();
                case 8 -> deleteMovie();
                case 11 -> findMoviesOfActor();
                case 12 -> findActorsOfMovie();
                case 13 -> addActorToMovie();
                case 14 -> deleteActorFromMovie();

                default -> System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private void deleteActorFromMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give actor's name:");
        String name = scanner.nextLine();
        System.out.println("Give movie's title:");
        String title = scanner.nextLine();

        Optional<Person> actor = actorService.findByName(name);
        Optional<Movie> movie = movieService.findByTitle(title);

        System.out.println("Movie: " + movie);
        System.out.println("Actor: " + actor);

        actor.ifPresent(a -> movie.ifPresent(m -> movieService.deleteActorFromMovie(a, m)));
    }

    private void addActorToMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give actor's name:");
        String name = scanner.nextLine();
        System.out.println("Give movie's title:");
        String title = scanner.nextLine();

        Optional<Person> actor = actorService.findByName(name);
        Optional<Movie> movie = movieService.findByTitle(title);

        System.out.println("Movie: " + movie);
        System.out.println("Actor: " + actor);

        actor.ifPresent(a -> movie.ifPresent(m -> movieService.addActorToMovie(a, m)));
    }

    private void findActorsOfMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give movie's title:");
        String title = scanner.nextLine();

        Optional<Movie> movie = movieService.findByTitle(title);
        System.out.println("Actors of: " + movie);

        movie.ifPresent(x -> actorService.findByMovie(x).forEach(System.out::println));
    }

    private void findMoviesOfActor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give actor's name:");
        String name = scanner.nextLine();

        Optional<Person> actor = actorService.findByName(name);
        System.out.println("Movies of: " + actor);

        actor.ifPresent(x -> movieService.findByActor(x).forEach(System.out::println));
    }

    void addActor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give actor's name:");
        String name = scanner.nextLine();
        System.out.println("Give actor's born year:");
        long bornYear = scanner.nextLong();

        System.out.println(actorService.create(name, bornYear));
    }

    void deleteActor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give actor's name to delete:");
        String name = scanner.nextLine();

        Optional<Person> person = actorService.findByName(name);
        System.out.println("To delete: " + person);
        person.ifPresent(actorService::delete);
    }

    void addMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give movie's title:");
        String title = scanner.nextLine();
        System.out.println("Give movie's release year:");
        long released = scanner.nextLong();

        System.out.println(movieService.create(title, released));
    }

    void deleteMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give movie's title to delete:");
        String title = scanner.nextLine();

        Optional<Movie> movie = movieService.findByTitle(title);
        System.out.println("To delete: " + movie);
        movie.ifPresent(movieService::delete);
    }
}
