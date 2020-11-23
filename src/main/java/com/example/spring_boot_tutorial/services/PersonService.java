package com.example.spring_boot_tutorial.services;

import com.example.spring_boot_tutorial.dao.PersonDao;
import com.example.spring_boot_tutorial.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public Person addPerson(Person person){
        return personDao.insertPerson(person);
    }
    public List<Person> getPeople() {return personDao.getPeople();}
}
