package com.example.spring_boot_tutorial.dao;

import com.example.spring_boot_tutorial.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<Person>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    public void printDB(){
        DB.forEach(person -> {
            System.out.println(person);
        });
    }
}
