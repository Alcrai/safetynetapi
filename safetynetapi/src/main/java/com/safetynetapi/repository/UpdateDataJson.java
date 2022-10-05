package com.safetynetapi.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.emitter.ScalarAnalysis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UpdateDataJson implements IUpdateData{
    private List<Person> persons;
    private List<FireStation> fireStations;
    private List<MedicalRecord> medicalRecords;

    private String filePath = "safetynetapi/src/main/resources/update.json";

    public UpdateDataJson(List<Person> persons, List<FireStation> fireStations, List<MedicalRecord> medicalRecords) {
        this.persons = persons;
        this.fireStations = fireStations;
        this.medicalRecords = medicalRecords;
    }

    public void writeFileJson(List<Person> persons, List<FireStation> fireStations, List<MedicalRecord> medicalRecords){

        ObjectMapper mapper = new ObjectMapper();
        try {
            String fileData;
            fileData="{\n\"persons\":[\n"+ mapper.writeValueAsString(persons)+"\n],\n"+
                    "\"firestations\":[\n"+mapper.writeValueAsString(fireStations)+"\n],\n"+
                    "\"medicalrecords\":[\n"+mapper.writeValueAsString(medicalRecords)+"\n]\n}";
            FileWriter fw = new FileWriter(filePath);
            fw.write(fileData);
            fw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public Person delete(Person person) {
        return null;
    }

    @Override
    public FireStation save(FireStation fireStation) {
        return null;
    }

    @Override
    public FireStation update(FireStation fireStation) {
        return null;
    }

    @Override
    public FireStation delete(FireStation fireStation) {
        return null;
    }

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        return null;
    }

    @Override
    public MedicalRecord update(MedicalRecord medicalRecord) {
        return null;
    }

    @Override
    public MedicalRecord delete(MedicalRecord medicalRecord) {
        return null;
    }
}

