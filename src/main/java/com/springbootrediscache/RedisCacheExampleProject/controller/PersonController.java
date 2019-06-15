package com.springbootrediscache.RedisCacheExampleProject.controller;

import com.springbootrediscache.RedisCacheExampleProject.entitys.Person;
import com.springbootrediscache.RedisCacheExampleProject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = {"/person"})
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = {"/getPerson/{id}"})
    @ResponseBody
    public ResponseEntity<Person> getPerson(@PathVariable long id) {
        Person person = personService.getPerson(id);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @PostMapping(value = {"/addPerson"})
    @ResponseBody
    public ResponseEntity<Person> addPerson(@RequestBody Person person, UriComponentsBuilder builder) {
        personService.savePerson(person);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("addPerson/{id}").buildAndExpand(person.getId()).toUri());
        return new ResponseEntity<Person>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = {"/updatePerson"})
    @ResponseBody
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @DeleteMapping(value = {"/deletePerson/{id}"})
    public ResponseEntity<Void> deletePerson(@PathVariable long id) {
        personService.deletePerson(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
}
