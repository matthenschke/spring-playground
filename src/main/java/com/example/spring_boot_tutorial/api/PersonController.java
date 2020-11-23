package com.example.spring_boot_tutorial.api;

import com.example.spring_boot_tutorial.models.Person;
import com.example.spring_boot_tutorial.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//, produces = MediaType.APPLICATION_JSON_VALUE
@RequestMapping(value ="/api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        Person newPerson = personService.addPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Person>> getPeople(){
        return new ResponseEntity<>(personService.getPeople(), HttpStatus.OK);
    }
}
