package com.safetynetapi.controller;


import com.safetynetapi.model.Person;
import com.safetynetapi.repository.LoadingData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    private final LoadingData loadingData;

    public PersonController(LoadingData loadingData) {
        this.loadingData = loadingData;
    }

    @GetMapping("/person")
    public List<Person> ListPersons() {
        return loadingData.findAllPerson() ;
    }

}
