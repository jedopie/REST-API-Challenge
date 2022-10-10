package com.example.user.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.user.model.Person;
import com.example.user.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin() // open for all port
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired PersonRepository personRepository;

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getPeople() {
        try {
            return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/person{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Long id) {
        try {
            //check if teacher exist in database
            Person person = getPersonId(id);

            if (person != null) {
                return new ResponseEntity<>(person, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        Person existingPerson = getPersonId(person.getId());

        if (existingPerson != null) {
            existingPerson.setName(person.getName());
            existingPerson.setAddress(person.getAddress());
            existingPerson.setPostcode(person.getPostcode());
            existingPerson.setAge(person.getAge());
            existingPerson.setEmail(person.getEmail());
            existingPerson.setJob(person.getJob());
            existingPerson.setPhone(person.getPhone());

            return new ResponseEntity<>(personRepository.save(existingPerson), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Person> newPerson(@RequestBody Person person) {
        Person newPerson = personRepository
                .save(Person.builder()
                        .id(person.getId())
                        .name(person.getName())
                        .address(person.getAddress())
                        .postcode(person.getPostcode())
                        .age(person.getAge())
                        .job(person.getJob())
                        .email(person.getEmail())
                        .phoneno(person.getPhone())
                        .build());
        return new ResponseEntity<>(newPerson, HttpStatus.OK);
    }

    @DeleteMapping("/person")
    public ResponseEntity<Person> deleteTeacher(@RequestBody Person person) {
        try {
            Person existingPerson = getPersonId(person.getId());

            if (existingPerson != null) {
                personRepository.deleteById(person.getId());
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Person getPersonId(long id) {
        Optional<Person> person = personRepository.findById(id);

        if (person.isPresent()) {
            return person.get();
        }
        return null;
    }

}