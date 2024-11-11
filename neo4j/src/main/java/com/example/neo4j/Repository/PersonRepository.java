package com.example.neo4j.Repository;

import com.example.neo4j.Model.Movie;
import com.example.neo4j.Model.Person;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class PersonRepository {

    private final Driver driver;

    public PersonRepository(Driver driver) {
        this.driver = driver;
    }

    public Optional<Person> save(Person person) {
        String cypherQuery = "CREATE (p:Person {name: $name, born: $born}) RETURN p";
        Map<String, Object> cypherParameters = Map.of("name", person.getName(), "born", person.getBorn());

        var records = driver.executableQuery(cypherQuery)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .withParameters(cypherParameters)
                .execute()
                .records();

        return records.stream()
                .map(this::mapRecordToPerson)
                .findFirst();
    }

    public Iterable<Person> findAll() {
                String cypherQuery = "MATCH (p:Person) RETURN p";

        var records = driver.executableQuery(cypherQuery)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .execute()
                .records();

        return records.stream()
                .map(this::mapRecordToPerson)
                .toList();
    }

    public Optional<Person> findByName(String name) {
        String cypherQuery = "MATCH (p:Person) WHERE p.name = $name RETURN p";
        Map<String, Object> cypherParameters = Map.of("name", name);

        var records = driver.executableQuery(cypherQuery)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .withParameters(cypherParameters)
                .execute()
                .records();

        return records.stream()
                .map(this::mapRecordToPerson)
                .findFirst();
    }

    private Person mapRecordToPerson(Record record) {
        var personNode = record.get("p");
        var name = personNode.get("name").asString();
        var born = personNode.get("born").asLong();

        return new Person(name, born);
    }

    public void deleteByName(String name) {
        String cypherQuery = "MATCH (p:Person) WHERE p.name = $name DETACH DELETE p";
        Map<String, Object> cypherParameters = Map.of("name", name);

        var records = driver.executableQuery(cypherQuery)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .withParameters(cypherParameters)
                .execute();
    }

    public Iterable<Person> findByMovie(Movie movie) {
        String cypherQuery = "MATCH (p:Person)-[:ACTED_IN]->(m:Movie) WHERE m.title = $title RETURN p";
        Map<String, Object> cypherParameters = Map.of("title", movie.getTitle());

        var records = driver.executableQuery(cypherQuery)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .withParameters(cypherParameters)
                .execute()
                .records();

        return records.stream()
                .map(this::mapRecordToPerson)
                .toList();
    }
}