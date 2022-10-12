package com.safetynetapi.controller;

import com.safetynetapi.dto.ChildAlertDTO;
import com.safetynetapi.dto.FireDTO;
import com.safetynetapi.dto.FloodDTO;
import com.safetynetapi.dto.PersonInfoDTO;
import com.safetynetapi.service.IAlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class AlertControllerTest {

    @Mock
    private IAlertService alertService;

    private AlertController alertController;

    @BeforeEach
    public void init(){
        alertController = new AlertController(alertService);
    }

    @Test
    public void ListChildAlertReturnList(){
        List<String> family = new ArrayList<>();
        family.add("Harry");
        List<ChildAlertDTO> childAlertDTOs = new ArrayList<>();
        childAlertDTOs.add(new ChildAlertDTO("Alex","Blandio",12, family));
        when(alertService.childAlertService("1 Culver St")).thenReturn(childAlertDTOs);
        assertThat(alertController.listChildAlert("1 Culver St")).size().isEqualTo(1);
        verify(alertService).childAlertService("1 Culver St");
    }

    @Test
    public void ListPhoneAlertReturnAList(){
        Set<String> phones =new HashSet<>();
        phones.add("123-123-123");
        when(alertService.phoneAlertService("6")).thenReturn(phones);
        assertThat(alertController.listPhoneAlert("6")).size().isEqualTo(1);
        verify(alertService).phoneAlertService("6");
    }

    @Test
    public void ListFireReturnAList(){
        List<FireDTO> fire = new ArrayList<>();
        fire.add(new FireDTO("Alex","Blandio","1 Culver St","Culver","6","123-123-123",24,"[]","[]"));
        when(alertService.fireService("1 Culver St")).thenReturn(fire);
        assertThat(alertController.listFire("1 Culver St")).size().isEqualTo(1);
        verify(alertService).fireService("1 Culver St");
    }

    @Test
    public void listPersonOfStationReturnFloodDTO(){
        List<FloodDTO> flood = new ArrayList<>();
        flood.add(new FloodDTO("1 Culver St","6","Alex","Blandio","123-123-123",24,"[]","[]"));
        when(alertService.personOfStationService("6")).thenReturn(flood);
        assertThat(alertController.listPersonOfStation("6")).size().isEqualTo(1);
        verify(alertService).personOfStationService("6");
    }

    @Test
    public void listPersonWithMedicalrecordReturnAListPersonInfo(){
        List<PersonInfoDTO> personInfo = new ArrayList<>();
        personInfo.add(new PersonInfoDTO("Alex","Blandio","1 Culver St","Culver","alex@mail.com",24,"[]","[]"));
        when(alertService.personWithMedicalRecordService("Alex","Blandio")).thenReturn(personInfo);
        assertThat(alertController.listPersonWithMedicalRecord("Alex","Blandio")).size().isEqualTo(1);
        verify(alertService).personWithMedicalRecordService("Alex","Blandio");
    }

    @Test
    public void ListEmailPerCityReturnaList(){
        Set<String> email =new HashSet<>();
        email.add("alex@mail.com");
        when(alertService.emailPerCityService("Culver")).thenReturn(email);
        assertThat(alertController.listEmailPerCity("Culver")).size().isEqualTo(1);
        verify(alertService).emailPerCityService("Culver");
    }




}

