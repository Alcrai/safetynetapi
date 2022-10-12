package com.safetynetapi.repository;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.service.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class LoadDataTest {
    @Mock
    private LoadDAO loadDAO;
    public List<Person> persons;
    public List<FireStation> fireStations;
    public List<MedicalRecord> medicalRecords;

    @BeforeEach
    public void initTest(){
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
    public void findAllPersonReturnAList(){
        when(loadDAO.findAllPerson()).thenReturn(persons);
        assertThat(loadDAO.findAllPerson()).size().isEqualTo(persons.size());
        verify(loadDAO).findAllPerson();
    }

    @Test
    public void findAllMedicalrecordReturnAList(){
        when(loadDAO.findAllMedicalRecord()).thenReturn(medicalRecords);
        assertThat(loadDAO.findAllMedicalRecord()).size().isEqualTo(medicalRecords.size());
        verify(loadDAO).findAllMedicalRecord();
    }

    @Test
    public void findAllFireStationReturnAList(){
        when(loadDAO.findAllFireStation()).thenReturn(fireStations);
        assertThat(loadDAO.findAllFireStation()).size().isEqualTo(fireStations.size());
        verify(loadDAO).findAllFireStation();
    }

    @Test
    public void saveFireStationReturnFireStation(){
        FireStation fireStation = new FireStation("1 Culver St","6");
        when(loadDAO.saveFireStation(fireStation)).thenReturn(fireStation);
        assertThat(loadDAO.saveFireStation(fireStation)).isEqualTo(fireStation);
        verify(loadDAO).saveFireStation(fireStation);
    }


}
