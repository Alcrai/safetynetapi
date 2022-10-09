package com.safetynetapi.controller;


import com.safetynetapi.model.Person;
import com.safetynetapi.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class PersonController {
   private IPersonService personService;
    @Autowired
    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public List<Person> ListPersons() {
        return personService.findAllPerson() ;
    }

    @PostMapping("/person")
    public ResponseEntity<Person> newPerson(@RequestBody Person person){
        Person personAdded = personService.save(person);
        if(Objects.isNull(personAdded)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(personAdded)
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/person")
    public ResponseEntity<Person> updatePerson(@RequestParam("firstName")String firstName,@RequestParam("lastName") String lastName,@RequestBody Person person){
        Person personAdded = personService.updatePerson(firstName,lastName,person);
        if(Objects.isNull(personAdded)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(personAdded)
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/person")
    public Map<String,Boolean> deletePerson(@RequestParam("firstName") String firstName,@RequestParam("lastName")String lastName){
        Person personDelete = personService.deletePerson(firstName,lastName);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
