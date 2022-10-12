package com.safetynetapi.service;

import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.repository.ILoadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecordService{
   private ILoadDAO loadData;

    @Autowired
    public MedicalRecordService(ILoadDAO loadData) {
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
