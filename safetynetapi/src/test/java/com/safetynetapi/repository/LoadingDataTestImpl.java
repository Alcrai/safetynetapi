package com.safetynetapi.repository;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;

import java.util.ArrayList;
import java.util.List;

public class LoadingDataTestImpl implements ILoadingData{

    private List<Person> persons ;
    private List<FireStation> fireStations;
    private List<MedicalRecord> medicalRecords;

    public LoadingDataTestImpl() {
        this.persons = this.findAllPerson();
        this.fireStations = this.findAllFireStation();
        this.medicalRecords = this.findAllMedicalRecord();
    }

    private List<MedicalRecord> findAllMedicalRecord() {
        medicalRecords = new ArrayList<>();
        medicalRecords.add(new MedicalRecord("Alex","Blandio","03/06/2020", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]"));
        medicalRecords.add(new MedicalRecord("Cat","Blandio","03/06/1984", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]"));
        return medicalRecords;
    }

    private List<FireStation> findAllFireStation() {
        fireStations = new ArrayList<>();
        fireStations.add(new FireStation("1509 Culver St","1"));
        fireStations.add(new FireStation("25 Vosges St","2"));
        return fireStations;
    }

    private List<Person> findAllPerson() {
        persons = new ArrayList<>();
        persons.add(new Person("Alex","Blandio","1509 Culver St","Culver","97451","841-874-6512", "alexblandio@email.com"));
        persons.add(new Person("Cat","Blandio","1509 Culver St","Culver","97451","841-874-6512", "catblandio@email.com"));
        return persons;
    }

    @Override
    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public List<FireStation> getFireStations() {
        return fireStations;
    }

    @Override
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
}
