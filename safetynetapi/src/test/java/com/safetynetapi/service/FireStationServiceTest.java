package com.safetynetapi.service;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadData;
import com.safetynetapi.repository.ILoadingData;
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
public class FireStationServiceTest {
    @Mock
    private ILoadData loadData;

    private FireStationService fireStationService;

    public List<Person> persons;
    public List<FireStation> fireStations;
    public List<MedicalRecord> medicalRecords;

    @BeforeEach
    public void initTest(){
        fireStationService = new FireStationService(loadData);
        persons=new ArrayList<>();
        fireStations=new ArrayList<>();
        medicalRecords=new ArrayList<>();
        persons.add(new Person("Alex","Blandio","1509 Culver St","Culver","97451","841-874-6512", "alexblandio@email.com"));
        persons.add(new Person("Cat","Blandio","1509 Culver St","Culver","97451","841-874-6512", "catblandio@email.com"));
        fireStations.add(new FireStation("1509 Culver St","1"));
        medicalRecords.add(new MedicalRecord("Alex","Blandio","03/06/2020", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]"));
        medicalRecords.add(new MedicalRecord("Cat","Blandio","03/06/1984", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]"));


    }
    @Test
    public void personOfStationServiceReturnThreeFireStationDto(){
        when(loadData.findAllPerson()).thenReturn(persons);
        when(loadData.findAllFireStation()).thenReturn(fireStations);
        when(loadData.findAllMedicalRecord()).thenReturn(medicalRecords);
        String station = "1";
        assertThat(fireStationService.personOfStationService(station)).size().isEqualTo(3);
        verify(loadData).findAllPerson();
        verify(loadData).findAllFireStation();
        verify(loadData).findAllMedicalRecord();
    }
}
