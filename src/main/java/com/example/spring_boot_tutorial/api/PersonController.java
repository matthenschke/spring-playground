package com.example.spring_boot_tutorial.api;

import com.example.spring_boot_tutorial.models.Person;
import com.example.spring_boot_tutorial.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Person> addPerson(@Valid @NotNull @RequestBody Person person){
        Person newPerson = personService.addPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Person>> getPeople(){
        return new ResponseEntity<>(personService.getPeople(), HttpStatus.OK);
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable UUID id){
        Person p = personService.getPersonById(id).orElse(null);
        if (p == null)
            return new ResponseEntity<Person>(p, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Person> deletePersonById(@PathVariable UUID id) {
        Person p = personService.deletePersonById(id).orElse(null);
        if (p == null)
            return new ResponseEntity<Person>(p, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable UUID id, @Valid @NotNull @RequestBody Person person){
        Person p = personService.updatePerson(id, person).orElse(null);
        if (p == null)
            return new ResponseEntity<Person>(p, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            ((HashMap) errors).put(fieldName, errorMessage);
        });
        return errors;
    }
}
