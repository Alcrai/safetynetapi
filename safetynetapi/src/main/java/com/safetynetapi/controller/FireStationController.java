package com.safetynetapi.controller;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadingData;
import com.safetynetapi.service.FireStationService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FireStationController {

    private ILoadingData iLoadingData;
    private FireStationService fireStationService;

    public FireStationController(ILoadingData iLoadingData, FireStationService fireStationService) {
        this.iLoadingData = iLoadingData;
        this.fireStationService = fireStationService;
    }

    @GetMapping("/firestation")
    public List<String> listOfPersonOfStation(@RequestParam("stationNumber") String station_number){
        fireStationService = new FireStationService(iLoadingData);
        return fireStationService.personOfStationService(station_number);
    }

}
