package com.safetynetapi.service;

import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.repository.ILoadData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {
    @Mock
    private ILoadData loadData;
    private MedicalRecordService medicalRecordService;
    public List<MedicalRecord> medicalRecords;

    @BeforeEach
    public void initTest(){
        medicalRecordService = new MedicalRecordService(loadData);
        medicalRecords=new ArrayList<>();
        medicalRecords.add(new MedicalRecord("Alex","Blandio","03/06/2020", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]"));
        medicalRecords.add(new MedicalRecord("Cat","Blandio","03/06/1984", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]"));
    }

    @Test
    public void findAllMedicalRecordReturnAList(){
        when(loadData.findAllMedicalRecord()).thenReturn(medicalRecords);
        assertThat(medicalRecordService.findAllMedicalRecord()).size().isEqualTo(2);
        verify(loadData).findAllMedicalRecord();
    }

    @Test
    public void saveMedicalRecordReturnMedicalRecord(){
        MedicalRecord medicalRecord = new MedicalRecord("Alex","Blandio","03/06/2020", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]");
        when(loadData.saveMedicalRecord(medicalRecord)).thenReturn(medicalRecord);
        assertThat(medicalRecordService.saveMedicalRecord(medicalRecord)).isEqualTo(medicalRecord);
        verify(loadData).saveMedicalRecord(medicalRecord);
    }

    @Test
    public void updateMedicalRecordReturnMedicalRecord(){
        MedicalRecord medicalRecord = new MedicalRecord("Alex","Blandio","03/06/2020", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]");
        when(loadData.updateMedicalRecord("Alex","Blandio",medicalRecord)).thenReturn(medicalRecord);
        assertThat(medicalRecordService.updateMedicalRecord("Alex","Blandio",medicalRecord)).isEqualTo(medicalRecord);
        verify(loadData).updateMedicalRecord("Alex","Blandio",medicalRecord);
    }

    @Test
    public void deleteMedicalRecordReturnMedicalRecord(){
        MedicalRecord medicalRecord = new MedicalRecord("Alex","Blandio","03/06/2020", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]");
        when(loadData.deleteMedicalRecord("Alex","Blandio")).thenReturn(medicalRecord);
        assertThat(medicalRecordService.deleteMedicalRecord("Alex","Blandio")).isEqualTo(medicalRecord);
        verify(loadData).deleteMedicalRecord("Alex","Blandio");
    }

}
