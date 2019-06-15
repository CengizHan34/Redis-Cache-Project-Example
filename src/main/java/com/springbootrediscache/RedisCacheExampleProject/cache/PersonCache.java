package com.springbootrediscache.RedisCacheExampleProject.cache;

import com.springbootrediscache.RedisCacheExampleProject.entitys.Person;
import com.springbootrediscache.RedisCacheExampleProject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonCache {
    private PersonRepository personRepository;

    @Autowired
    public PersonCache(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @CachePut(value = "personCache", key = "#id", condition = "#result != null")
    public void addPerson(Person person) {
        System.out.println("In addItem cache component..");
        personRepository.save(person);
    }

    @Cacheable(value = "personCache", key = "#id")
    public Person getPerson(long id) {
        System.out.println("In getItem cache Component..");
        Optional<Person> person = null;
        try {
            person = personRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person.get();
    }

    @CacheEvict(value = "personCache", key = "#id")
    public void deletePerson(long id) {
        System.out.println("In deletePerson cache Component..");
        personRepository.deleteById(id);
    }

    @CachePut(value = "personCache", key = "#id", condition = "#result != null")
    public void updatePerson(Person person) {
        System.out.println("In updatePerson cache Component..");
        personRepository.save(person);
    }
}
