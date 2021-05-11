package com.lab11spring.lab11spring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class MethodsController {

    private final List<Person> persons = new ArrayList<>();

    public MethodsController() {
        persons.add(new Person("gicu", 8));
        persons.add(new Person("gigel", 9));
    }

    @GetMapping("/count")
    public int countPersons() {
        return persons.size();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        return persons.get(id);
    }

    //--------------------------------------------------------------------------

    @GetMapping
    public List<Person> getPersons() {
        return persons;
    }

    //--------------------------------------------------------------------------

    @PostMapping
    public int createPerson(@RequestParam String name, int age) {
        persons.add(new Person(name, age));
        return persons.size();
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<String>
    createPerson(@RequestBody Person person) {
        persons.add(person);
        return new ResponseEntity<>(
                "Person added successfully", HttpStatus.CREATED);
    }

    //--------------------------------------------------------------------------

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<String>
    updatePerson(@PathVariable("id") int id, @RequestParam String name) {
        if (id > persons.size()) {
            return new ResponseEntity<>(
                    "Person not found", HttpStatus.NOT_FOUND); //or GONE
        }
        Person person = persons.get(id);
        person.setName(name);
        return new ResponseEntity<>(
                "Person updated successfully", HttpStatus.OK);
    }

    //--------------------------------------------------------------------------

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        if (id > persons.size()) {
            return new ResponseEntity<>(
                    "Person not found", HttpStatus.GONE);
        }
        persons.remove(id);
        return new ResponseEntity<>("Person removed", HttpStatus.OK);
    }


}

