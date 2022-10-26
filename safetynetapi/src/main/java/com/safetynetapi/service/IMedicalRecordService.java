package com.safetynetapi.service;

import com.safetynetapi.model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordService {
    List<MedicalRecord> findAllMedicalRecord();


    MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord);

    MedicalRecord deleteMedicalRecord(String firstName, String lastName);
}
