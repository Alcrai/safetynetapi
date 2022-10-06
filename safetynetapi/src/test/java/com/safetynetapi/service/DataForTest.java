package com.safetynetapi.service;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;

import java.util.List;

public class DataForTest {
    public static List<Person> persons;
    public static List<FireStation> fireStations;
    public static List<MedicalRecord> medicalRecords;

    static{
        persons.add(new Person("Alex","Blandio","1509 Culver St","Culver","97451","841-874-6512", "alexblandio@email.com"));
        persons.add(new Person("Cat","Blandio","1509 Culver St","Culver","97451","841-874-6512", "catblandio@email.com"));
        fireStations.add(new FireStation("1509 Culver St","1"));
        medicalRecords.add(new MedicalRecord("Alex","Blandio","03/06/2020", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]"));
        medicalRecords.add(new MedicalRecord("Cat","Blandio","03/06/1984", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]"));
    }

    public DataForTest() {
    }

    public static List<Person> getPersons() {
        return persons;
    }

    public static List<FireStation> getFireStations() {
        return fireStations;
    }

    public static List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
}
