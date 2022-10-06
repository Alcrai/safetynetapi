package com.safetynetapi.service;

import com.safetynetapi.dto.ChildAlertDto;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadingData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlertServiceTest {

    @Mock
    private ILoadingData iLoadingData;

    private AlertService alertService;

    public List<Person> persons;
    public List<FireStation> fireStations;
    public List<MedicalRecord> medicalRecords;

    @BeforeEach
    public void initTest(){
        alertService = new AlertService(iLoadingData);
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
        when(iLoadingData.findAllPerson()).thenReturn(persons);
        when(iLoadingData.findAllMedicalRecord()).thenReturn(medicalRecords);
        List<String> family = new ArrayList<>();
        family.add("firstName: Cat lastName: Blandio");
       List<ChildAlertDto> child =new ArrayList<>();
       child.add(new ChildAlertDto("Alex","Blandio",2,family));

       assertThat(alertService.childAlertService("1509 Culver St").size()).isEqualTo(child.size());
       verify(iLoadingData).findAllPerson();
       verify(iLoadingData).findAllMedicalRecord();
    }

    @Test
    public void phoneAlertServiceReturnNumber(){
        when(iLoadingData.findAllPerson()).thenReturn(persons);
        when(iLoadingData.findAllFireStation()).thenReturn(fireStations);
        String stationNumber="1";
        assertThat(alertService.phoneAlertService(stationNumber)).first().isEqualTo("phone : 841-874-6512");
        verify(iLoadingData).findAllPerson();
        verify(iLoadingData).findAllFireStation();
    }

    @Test
    public void fireServiceReturnTwoFireDto(){
        when(iLoadingData.findAllPerson()).thenReturn(persons);
        when(iLoadingData.findAllFireStation()).thenReturn(fireStations);
        when(iLoadingData.findAllMedicalRecord()).thenReturn(medicalRecords);
        String address = "1509 Culver St";
        assertThat(alertService.fireService(address)).size().isEqualTo(2);
        verify(iLoadingData).findAllPerson();
        verify(iLoadingData).findAllFireStation();
        verify(iLoadingData).findAllMedicalRecord();
    }

    @Test
    public void personOfStationServiceReturn2FloodDto(){
        when(iLoadingData.findAllPerson()).thenReturn(persons);
        when(iLoadingData.findAllFireStation()).thenReturn(fireStations);
        when(iLoadingData.findAllMedicalRecord()).thenReturn(medicalRecords);
        String stations = "1";
        assertThat(alertService.personOfStationService(stations)).size().isEqualTo(2);
        verify(iLoadingData).findAllPerson();
        verify(iLoadingData).findAllFireStation();
        verify(iLoadingData).findAllMedicalRecord();
    }

    @Test
    public void personWithMedicalRecordServiceReturnOnePersonInfoDto(){
        when(iLoadingData.findAllPerson()).thenReturn(persons);
        when(iLoadingData.findAllMedicalRecord()).thenReturn(medicalRecords);
        String firstName = "Alex";
        String lastName = "Blandio";
        assertThat(alertService.personWithMedicalRecordService(firstName,lastName)).size().isEqualTo(1);
        verify(iLoadingData).findAllPerson();
        verify(iLoadingData).findAllMedicalRecord();
    }

    @Test
    public void EmailPerCityServiceReturnTwoMail(){
        when(iLoadingData.findAllPerson()).thenReturn(persons);
        String city = "Culver";
        assertThat(alertService.EmailPerCityService(city)).size().isEqualTo(2);
        verify(iLoadingData).findAllPerson();
    }



}
