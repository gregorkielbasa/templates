package com.example.springjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

interface PersonRepository extends JpaRepository<Person, Long> {

    Person save(Person person);

    Optional<Person> findById(long id);
}
