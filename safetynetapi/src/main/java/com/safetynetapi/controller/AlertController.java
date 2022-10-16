package com.safetynetapi.controller;

import com.safetynetapi.dto.ChildAlertDTO;
import com.safetynetapi.dto.FireDTO;
import com.safetynetapi.dto.FloodDTO;
import com.safetynetapi.dto.PersonInfoDTO;
import com.safetynetapi.service.AlertService;
import com.safetynetapi.service.IAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class AlertController {
    private IAlertService alertService;
    @Autowired
    public AlertController(IAlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/childAlert")
    public List<ChildAlertDTO> getChildAlert(@RequestParam("address") String address){
        return alertService.childAlertService(address);
    }

    @GetMapping("/phoneAlert")
    public Set<String> getPhoneAlert(@RequestParam("firestation") String fireStationNumber){
        return alertService.phoneAlertService(fireStationNumber);
    }

    @GetMapping("/fire")
    public List<FireDTO> getFire(@RequestParam("address") String address){
        return alertService.fireService(address);
    }

    @GetMapping("/flood/stations")
    public List<FloodDTO> getFloodStation(@RequestParam("stations") String stations){
        return alertService.personOfStationService(stations);
    }

    @GetMapping("/personInfo")
    public List<PersonInfoDTO> getPersoninfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return alertService.personWithMedicalRecordService(firstName,lastName);
    }

    @GetMapping("/communityEmail")
    public Set<String> getCommunityEmail(@RequestParam("city") String city){
        return alertService.emailPerCityService(city);
    }
}
