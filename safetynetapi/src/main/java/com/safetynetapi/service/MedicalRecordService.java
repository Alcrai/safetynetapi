package com.safetynetapi.service;

import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadData;
import com.safetynetapi.repository.ILoadingData;
import com.safetynetapi.repository.LoadData;
import com.safetynetapi.repository.LoadingDataJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecordService{
   private ILoadData loadData;

    @Autowired
    public MedicalRecordService(ILoadData loadData) {
       this.loadData = loadData;
    }

    @Override
    public List<MedicalRecord> findAllMedicalRecord() {
        return loadData.findAllMedicalRecord();
    }

    @Override
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return loadData.saveMedicalRecord(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord) {
        return loadData.updateMedicalRecord( firstName,lastName, medicalRecord);
    }

    @Override
    public MedicalRecord deleteMedicalRecord(String firstName, String lastName) {
        return loadData.deleteMedicalRecord(firstName,lastName);
    }


}
