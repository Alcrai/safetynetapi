package com.safetynetapi.service;

import com.safetynetapi.dto.ChildAlertDTO;
import com.safetynetapi.dto.PersonDTO;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadDAO;
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
public class AlertServiceTest {

    @Mock
    private ILoadDAO loadData;

    private AlertService alertService;

    public List<Person> persons;
    public List<FireStation> fireStations;
    public List<MedicalRecord> medicalRecords;

    @BeforeEach
    public void initTest(){
        alertService = new AlertService(loadData);
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
    public void childAlertServiceReturnListOfChildAlertDto(){
        when(loadData.findAllPerson()).thenReturn(persons);
        when(loadData.findAllMedicalRecord()).thenReturn(medicalRecords);
        PersonDTO mom = new PersonDTO("Alex","Blandio");
        List<PersonDTO> family = new ArrayList<>();
        family.add(mom);
        List<ChildAlertDTO> child =new ArrayList<>();
        child.add(new ChildAlertDTO("Alex","Blandio",2,family));
        assertThat(alertService.childAlertService("1509 Culver St").size()).isEqualTo(child.size());
        verify(loadData).findAllPerson();
        verify(loadData).findAllMedicalRecord();
    }

    @Test
    public void phoneAlertServiceReturnNumber(){
        when(loadData.findAllPerson()).thenReturn(persons);
        when(loadData.findAllFireStation()).thenReturn(fireStations);
        String stationNumber="1";
        assertThat(alertService.phoneAlertService(stationNumber)).first().isEqualTo("841-874-6512");
        verify(loadData).findAllPerson();
        verify(loadData).findAllFireStation();
    }

    @Test
    public void fireServiceReturnTwoFireDto(){
        when(loadData.findAllPerson()).thenReturn(persons);
        when(loadData.findAllFireStation()).thenReturn(fireStations);
        when(loadData.findAllMedicalRecord()).thenReturn(medicalRecords);
        String address = "1509 Culver St";
        assertThat(alertService.fireService(address)).size().isEqualTo(2);
        verify(loadData).findAllPerson();
        verify(loadData).findAllFireStation();
        verify(loadData).findAllMedicalRecord();
    }

    @Test
    public void personOfStationServiceReturn2FloodDto(){
        when(loadData.findAllPerson()).thenReturn(persons);
        when(loadData.findAllFireStation()).thenReturn(fireStations);
        when(loadData.findAllMedicalRecord()).thenReturn(medicalRecords);
        String stations = "1";
        assertThat(alertService.personOfStationService(stations)).size().isEqualTo(2);
        verify(loadData).findAllPerson();
        verify(loadData).findAllFireStation();
        verify(loadData).findAllMedicalRecord();
    }

    @Test
    public void personWithMedicalRecordServiceReturnOnePersonInfoDto(){
        when(loadData.findAllPerson()).thenReturn(persons);
        when(loadData.findAllMedicalRecord()).thenReturn(medicalRecords);
        String firstName = "Alex";
        String lastName = "Blandio";
        assertThat(alertService.personWithMedicalRecordService(firstName,lastName)).size().isEqualTo(1);
        verify(loadData).findAllPerson();
        verify(loadData).findAllMedicalRecord();
    }

    @Test
    public void emailPerCityServiceReturnTwoMail(){
        when(loadData.findAllPerson()).thenReturn(persons);
        String city = "Culver";
        assertThat(alertService.emailPerCityService(city)).size().isEqualTo(2);
        verify(loadData).findAllPerson();
    }



}
