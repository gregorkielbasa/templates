package com.example.neo4j.Service;

import com.example.neo4j.Model.Movie;
import com.example.neo4j.Model.Person;
import com.example.neo4j.Repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorService {

    private final PersonRepository personRepository;

    public ActorService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    public Iterable<Person> findByMovie(Movie movie) {
        return personRepository.findByMovie(movie);
    }

    public Optional<Person> create(String name, long bornYear) {
        Person person = new Person(name, bornYear);
        return personRepository.save(person);
    }

    public void delete(Person person) {
        personRepository.deleteByName(person.getName());
    }
}
