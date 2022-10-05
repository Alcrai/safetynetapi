package com.safetynetapi.repository;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;

import java.util.List;

public interface ILoadingData {
    List<Person> findAllPerson();

    List<FireStation> findAllFireStation();

    List<MedicalRecord> findAllMedicalRecord();



}
