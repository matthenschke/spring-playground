package com.example.spring_boot_tutorial.dao;

import com.example.spring_boot_tutorial.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<Person>();
    @Override
    public Person insertPerson(UUID id, Person person) {
        Person newPerson = new Person(id, person.getName());
        DB.add(newPerson);
        return newPerson;
    }

    @Override
    public List<Person> getPeople() {
        return DB;
    }

    @Override
    public Optional<Person> deletePersonById(UUID id) {
        Optional<Person> person = getPersonById(id);
        if (!person.isEmpty())
        DB.remove(person.get());
        return person;
    }

    @Override
    public Optional<Person> updatePerson(UUID id, Person person) {
        Person newPerson = new Person(id, person.getName());
        return getPersonById(id).map(p -> {
            int index = DB.indexOf(p);
            if (index >= 0){
                DB.set(index, newPerson );
            }
            return newPerson;
        });
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

}
