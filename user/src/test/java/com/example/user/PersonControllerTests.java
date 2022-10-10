package com.example.user;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.user.repository.PersonRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.user.model.Person;
import com.example.user.controller.PersonController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTests {

    @MockBean
    PersonRepository personRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    public void test_allPeople_oneEntry_StatusOK() throws Exception {
        Person person = new Person(1L, "jed opie", "2/2 B St", "3070", "21", "Engineer", "j@email.com", "0402837982");
        List<Person> persons = Arrays.asList(person);

        when(personRepository.findAll()).thenReturn(persons);

        mockMvc.perform(get("/person/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("jed opie")));
    }

    @Test
    @Order(2)
    public void test_personById_correctId() throws Exception {
        Person person = new Person(1L, "jed opie", "2/2 B St", "3070", "21", "Engineer", "j@email.com", "0402837982");
        List<Person> persons = Arrays.asList(person);

        Optional<Person> p = Optional.of(person);
        Long id = 1L;

        when(personRepository.findById(id)).thenReturn(p);
        mockMvc.perform(get("/person/person1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)));
    }

    @Test
    @Order(3)
    public void test_personById_wrongId() throws Exception {
        Person person = new Person(1L, "jed opie", "2/2 B St", "3070", "21", "Engineer", "j@email.com", "0402837982");
        List<Person> persons = Arrays.asList(person);

        Optional<Person> p = Optional.empty();
        Long id = 4L;

        when(personRepository.findById(id)).thenReturn(p);
        mockMvc.perform(get("/person/person4"))
                .andExpect(status().isNotFound());
    }


}
