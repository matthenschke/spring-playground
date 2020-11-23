package com.example.spring_boot_tutorial.dao;

import com.example.spring_boot_tutorial.models.Person;

import java.util.List;
import java.util.UUID;

// here, we can use dependency injection to switch between different dbs
public interface PersonDao {
    Person insertPerson(UUID id, Person person);
    default Person insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
    List<Person> getPeople();
}
