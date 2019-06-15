package com.springbootrediscache.RedisCacheExampleProject.service;

import com.springbootrediscache.RedisCacheExampleProject.cache.PersonCache;
import com.springbootrediscache.RedisCacheExampleProject.entitys.Person;
import com.springbootrediscache.RedisCacheExampleProject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private PersonCache personCache;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonCache personCache) {
        this.personRepository = personRepository;
        this.personCache = personCache;
    }

    public Person getPerson(long id) {
        return personCache.getPerson(id);
    }

    public void savePerson(Person person) {
        personCache.addPerson(person);
    }

    public void updatePerson(Person person) {
        personCache.updatePerson(person);
    }

    public void deletePerson(long id) {
        personCache.deletePerson(id);
    }
}
