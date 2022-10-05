package com.safetynetapi.repository;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;

public interface IUpdateData {
    Person save(Person person);
    Person update(Person person);
    Person delete(Person person);
    FireStation save(FireStation fireStation);
    FireStation update(FireStation fireStation);
    FireStation delete(FireStation fireStation);
    MedicalRecord save(MedicalRecord medicalRecord);
    MedicalRecord update(MedicalRecord medicalRecord);
    MedicalRecord delete(MedicalRecord medicalRecord);
}
