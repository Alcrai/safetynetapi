package com.safetynetapi.controller;

import com.safetynetapi.dto.FireStationDto;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadingData;
import com.safetynetapi.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FireStationController {

    private ILoadingData iLoadingData;
    private FireStationService fireStationService;
    @Autowired
    public FireStationController(ILoadingData iLoadingData, FireStationService fireStationService) {
        this.iLoadingData = iLoadingData;
        this.fireStationService = fireStationService;
    }

    @GetMapping("/firestation")
    public List<FireStationDto> listOfPersonOfStation(@RequestParam("stationNumber") String station_number){
        return fireStationService.personOfStationService(station_number);
    }

}
