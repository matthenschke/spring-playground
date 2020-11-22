package com.example.spring_boot_tutorial.api;

import com.example.spring_boot_tutorial.models.Person;
import com.example.spring_boot_tutorial.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person){
        personService.addPerson(person);
        return new ResponseEntity<String>("Person added", HttpStatus.OK);
    }
}
