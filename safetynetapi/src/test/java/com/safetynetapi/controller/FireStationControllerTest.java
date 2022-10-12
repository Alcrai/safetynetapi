package com.safetynetapi.controller;

import com.safetynetapi.dto.FireStationDTO;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.Person;
import com.safetynetapi.service.IFireStationService;
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
public class FireStationControllerTest {
    @Mock
    private IFireStationService fireStationService;

    private FireStationController fireStationController;

    private List<FireStation> fireStations;
    private FireStation fireStation;

    @BeforeEach
    public void init(){
        fireStationController = new FireStationController(fireStationService);
        fireStations = new ArrayList<>();
        fireStation = new FireStation("1 Culver St","6");
        fireStations.add(fireStation);
    }

    @Test
    public void ListofPersonStationReturnAList(){
        FireStationDTO fireStationDTO = new FireStationDTO("Alex","Blandio","1 Culver St","Culver","123-123-123",1,0);
        List<FireStationDTO> fireStationDTOList = new ArrayList<>();
        fireStationDTOList.add(fireStationDTO);
        when(fireStationService.personOfStationService("6")).thenReturn(fireStationDTOList);
        assertThat(fireStationController.listOfPersonOfStation("6")).size().isEqualTo(1);
        verify(fireStationService).personOfStationService("6");
    }

    @Test
    public void ListFireStationReturnAList(){
        when(fireStationService.fireStationList()).thenReturn(fireStations);
        assertThat(fireStationController.ListStation()).size().isEqualTo(1);
        verify(fireStationService).fireStationList();
    }

    @Test
    public void deletePersonTestReturnMap(){
        when(fireStationService.delete("1 Culver St","6")).thenReturn(fireStation);
        assertThat(fireStationController.deleteFirestation("1 Culver St","6")).hasSize(1);
        verify(fireStationService).delete("1 Culver St","6");
    }
}
