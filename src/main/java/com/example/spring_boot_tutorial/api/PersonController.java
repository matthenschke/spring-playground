package com.example.spring_boot_tutorial.api;

import com.example.spring_boot_tutorial.models.Person;
import com.example.spring_boot_tutorial.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    @GetMapping(path = "{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable UUID id){
        return new ResponseEntity<>(personService.getPersonById(id).orElse(null), HttpStatus.OK);
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Person> deletePersonById(@PathVariable UUID id){
        return new ResponseEntity<>(personService.deletePersonById(id).orElse(null), HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable UUID id, @RequestBody Person person){
        return new ResponseEntity<>(personService.updatePerson(id, person).orElse(null), HttpStatus.OK);
    }
}
