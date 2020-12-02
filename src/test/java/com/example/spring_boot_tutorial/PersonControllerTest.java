package com.example.spring_boot_tutorial;

import com.example.spring_boot_tutorial.api.PersonController;
import com.example.spring_boot_tutorial.models.Person;
import com.example.spring_boot_tutorial.services.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;

    @Test
    public void addPersonShouldReturnPersonFromService() throws Exception{
        Person person = new Person(UUID.randomUUID(), "Bob");
        when(service.addPerson(any(Person.class))).thenReturn(person);
        this.mockMvc.perform(post("/api/v1/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(person))).andDo(print())
                .andExpect(status().isCreated()).andExpect(content().string(new ObjectMapper().writeValueAsString(person)));
    }

    @Test
    public void getPeopleShouldReturnPersonArray() throws Exception{
        List<Person>  personList = new ArrayList<Person>();
        Person [] personArr  = new Person[2];
        personArr[0] = new Person(UUID.randomUUID(), "foo");
        personArr[1] = new Person(UUID.randomUUID(), "bar");
        Collections.addAll(personList, personArr);
        when(service.getPeople()).thenReturn(personList);

        this.mockMvc.perform(get("/api/v1/person/")).
                andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(new ObjectMapper().writeValueAsString(personList)));
    }

    @Test
    public void getPersonByIdShouldReturnNotFoundWithEmptyResult() throws Exception {
        UUID id = UUID.randomUUID();
        when(service.getPersonById((id))).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/v1/person/{id}/", id)).
                andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void updatePersonShouldReturnNotFoundWithEmptyResult() throws Exception {
        UUID id = UUID.randomUUID();
        Person person = new Person(UUID.randomUUID(), "Bar");
        when(service.updatePerson(id, person)).thenReturn(Optional.empty());
        mockMvc.perform(put("/api/v1/person/{id}/", id).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(person))).
                andDo(print()).andExpect(status().isNotFound());
    }


    @Test
    public void deletePersonByIdShouldReturnNotFoundWithEmptyResult() throws Exception {
        UUID id = UUID.randomUUID();
        when(service.deletePersonById(id)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/v1/person/{id}/", id)).
                andDo(print()).andExpect(status().isNotFound());
    }
}
