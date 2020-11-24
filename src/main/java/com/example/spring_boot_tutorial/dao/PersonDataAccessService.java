package com.example.spring_boot_tutorial.dao;

import com.example.spring_boot_tutorial.models.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresDao")
public class PersonDataAccessService implements PersonDao {
    @Override
    public Person insertPerson(UUID id, Person person) {
        return null;
    }

    @Override
    public List<Person> getPeople() {
        return List.of(new Person(UUID.randomUUID(), "FROM POSTGRES DB"));
    }

    @Override
    public Optional<Person> deletePersonById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Person> updatePerson(UUID id, Person person) {
        return Optional.empty();
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return Optional.empty();
    }
}
