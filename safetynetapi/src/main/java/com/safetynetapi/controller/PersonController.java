package com.safetynetapi.controller;


import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadingData;
import com.safetynetapi.repository.UpdateDataJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private final ILoadingData ILoadingData;


    public PersonController(ILoadingData ILoadingData) {

        this.ILoadingData = ILoadingData;
    }

    @GetMapping("/person")
    public List<Person> ListPersons() {
        List<Person> persons = ILoadingData.findAllPerson();
        List<FireStation> fireStations = ILoadingData.findAllFireStation();
        List<MedicalRecord> medicalRecords = ILoadingData.findAllMedicalRecord();
        UpdateDataJson updateDataJson = new UpdateDataJson(persons,fireStations,medicalRecords);
        updateDataJson.writeFileJson(persons,fireStations,medicalRecords);
        return ILoadingData.findAllPerson() ;
    }

   /* @PostMapping("/person")
    public void savePerson(@RequestBody Person person){
        loadingData.findAllPerson();
        loadingData.save(person);

    }*/

}
