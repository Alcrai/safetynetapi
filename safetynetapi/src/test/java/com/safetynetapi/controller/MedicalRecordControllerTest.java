package com.safetynetapi.controller;

import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.service.IMedicalRecordService;
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
public class MedicalRecordControllerTest {
    @Mock
    private IMedicalRecordService medicalRecordService;

    private MedicalRecordController medicalRecordController;

    private List<MedicalRecord> medicalRecords;
    private MedicalRecord medicalRecord;

    @BeforeEach
    public void init(){
        medicalRecordController = new MedicalRecordController(medicalRecordService);
        medicalRecords = new ArrayList<>();
        medicalRecord = new MedicalRecord("Alex","Blandio","03/06/2020", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]");
        medicalRecords.add(medicalRecord);
    }

    @Test
    public void ListMedicalRecordsReturnAList(){
        when(medicalRecordService.findAllMedicalRecord()).thenReturn(medicalRecords);
        assertThat(medicalRecordController.listMedicalRecord()).size().isEqualTo(1);
        verify(medicalRecordService).findAllMedicalRecord();
    }
    @Test
    public void deletePersonTestReturnMap(){
        when(medicalRecordService.deleteMedicalRecord("Alex","Blandio")).thenReturn(medicalRecord);
        assertThat(medicalRecordController.deleteMedicalRecord("Alex","Blandio")).hasSize(1);
        verify(medicalRecordService).deleteMedicalRecord("Alex","Blandio");
    }
}
