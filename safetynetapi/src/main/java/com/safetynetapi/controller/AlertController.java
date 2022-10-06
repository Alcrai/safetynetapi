package com.safetynetapi.controller;

import com.safetynetapi.dto.ChildAlertDto;
import com.safetynetapi.dto.FireDto;
import com.safetynetapi.dto.FloodDto;
import com.safetynetapi.dto.PersonInfoDto;
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
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/childAlert")
    public List<ChildAlertDto> listChildAlert(@RequestParam("address") String address){
        return alertService.childAlertService(address);
    }

    @GetMapping("/phoneAlert")
    public Set<String> ListPhoneAlert(@RequestParam("firestation") String fireStationNumber){
        return alertService.phoneAlertService(fireStationNumber);
    }

    @GetMapping("/fire")
    public List<FireDto> listFire(@RequestParam("address") String address){
        return alertService.fireService(address);
    }

    @GetMapping("/flood/stations")
    public List<FloodDto> listPersonOfStation(@RequestParam("stations") String stations){
        return alertService.personOfStationService(stations);
    }

    @GetMapping("/personInfo")
    public List<PersonInfoDto> listPersonWithMedicalRecord(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return alertService.personWithMedicalRecordService(firstName,lastName);
    }

    @GetMapping("/communityEmail")
    public Set<String> ListEmailPerCity(@RequestParam("city") String city){
        return alertService.EmailPerCityService(city);
    }
}
