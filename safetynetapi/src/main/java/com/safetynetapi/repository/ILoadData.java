package com.safetynetapi.repository;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;

import java.util.List;

public interface ILoadData {
    List<Person> findAllPerson();

    List<FireStation> findAllFireStation();
    FireStation save(FireStation fireStation);

    List<MedicalRecord> findAllMedicalRecord();

    FireStation updateFireStation(String address, String station);

    FireStation deleteFireStation(String address, String station);

    Person savePerson(Person person);

    Person updatePerson(String firstName, String lastName, Person person);

    Person deletePerson(String firstName, String lastName);


    MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord);

    MedicalRecord deleteMedicalRecord(String firstName, String lastName);
}
