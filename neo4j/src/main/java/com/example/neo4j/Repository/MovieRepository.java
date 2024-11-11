package com.example.neo4j.Repository;

import com.example.neo4j.Model.Movie;
import com.example.neo4j.Model.Person;
import org.neo4j.driver.Driver;
import org.neo4j.driver.QueryConfig;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class MovieRepository {

    private final Driver driver;

    public MovieRepository(Driver driver) {
        this.driver = driver;
    }

    public Optional<Movie> save(Movie movie) {
        String cypherQuery = "CREATE (m:Movie {title: $title, released: $released}) RETURN m";
        Map<String, Object> cypherParameters = Map.of("title", movie.getTitle(), "released", movie.getReleased());

        var records = driver.executableQuery(cypherQuery)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .withParameters(cypherParameters)
                .execute()
                .records();

        return records.stream()
                .map(this::mapRecordToMovie)
                .findFirst();
    }

    public Iterable<Movie> findAll() {
        String cypherQuery = "MATCH (m:Movie) RETURN m";

        var records = driver.executableQuery(cypherQuery)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .execute()
                .records();

        return records.stream()
                .map(this::mapRecordToMovie)
                .toList();
    }

    public Optional<Movie> findByTitle(String title) {
        String cypherQuery = "MATCH (m:Movie) WHERE m.title = $title RETURN m";
        Map<String, Object> cypherParameters = Map.of("title", title);

        var records = driver.executableQuery(cypherQuery)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .withParameters(cypherParameters)
                .execute()
                .records();

        return records.stream()
                .map(this::mapRecordToMovie)
                .findFirst();
    }

    public Iterable<Movie> findByActor(Person actor) {
        String cypherQuery = "MATCH (m:Movie)<-[:ACTED_IN]-(a:Person) WHERE a.name = $name RETURN m";
        Map<String, Object> cypherParameters = Map.of("name", actor.getName());

        var records = driver.executableQuery(cypherQuery)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .withParameters(cypherParameters)
                .execute()
                .records();

        return records.stream()
                .map(this::mapRecordToMovie)
                .toList();
    }

    private Movie mapRecordToMovie(Record record) {
        var personNode = record.get("m");
        var title = personNode.get("title").asString();
        var released = personNode.get("released").asLong();

        return new Movie(title, released);
    }

    public void deleteByTitle(String title) {
        String cypherQuery = "MATCH (m:Movie) WHERE m.title = $title DETACH DELETE m";
        Map<String, Object> cypherParameters = Map.of("title", title);

        var records = driver.executableQuery(cypherQuery)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .withParameters(cypherParameters)
                .execute();
    }

    public void addActorToMovie(Movie movie, Person actor) {
        String cypherQuery = """
                MATCH (a:Person) WHERE a.name = $name
                MATCH (m:Movie) WHERE m.title = $title
                MERGE (a)-[:ACTED_IN]->(m)""";
        Map<String, Object> cypherParameters = Map.of("name", actor.getName(), "title", movie.getTitle());

        var records = driver.executableQuery(cypherQuery)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .withParameters(cypherParameters)
                .execute();
    }

    public void deleteActorFromMovie(Movie movie, Person actor) {
        String cypherQuery = """
                MATCH (a:Person)-[r:ACTED_IN]->(m:Movie)
                WHERE a.name = $name AND m.title = $title
                DELETE r""";
        Map<String, Object> cypherParameters = Map.of("name", actor.getName(), "title", movie.getTitle());

        var records = driver.executableQuery(cypherQuery)
                .withConfig(QueryConfig.builder().withDatabase("neo4j").build())
                .withParameters(cypherParameters)
                .execute();
    }
}