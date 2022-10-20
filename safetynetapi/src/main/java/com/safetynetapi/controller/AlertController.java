package com.safetynetapi.controller;

import com.safetynetapi.dto.ChildAlertDTO;
import com.safetynetapi.dto.FireDTO;
import com.safetynetapi.dto.FloodDTO;
import com.safetynetapi.dto.PersonInfoDTO;
import com.safetynetapi.service.IAlertService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
@Log4j2
@RestController
public class AlertController {
    private IAlertService alertService;
    @Autowired
    public AlertController(IAlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/childAlert")
    public List<ChildAlertDTO> getChildAlert(@RequestParam("address") String address){
        log.info("Request:GET /childAlert?address="+ address);
        log.info("Response:");
        alertService.childAlertService(address).forEach(as->log.info(as.toString()));
        return alertService.childAlertService(address);
    }

    @GetMapping("/phoneAlert")
    public Set<String> getPhoneAlert(@RequestParam("firestation") String fireStationNumber){
        log.info("Request:GET /phoneAlert?firestation="+ fireStationNumber);
        log.info("Response:");
        alertService.phoneAlertService(fireStationNumber).forEach(as->log.info(as.toString()));
        return alertService.phoneAlertService(fireStationNumber);
    }

    @GetMapping("/fire")
    public List<FireDTO> getFire(@RequestParam("address") String address){
        log.info("Request:GET /fire?address="+ address);
        log.info("Response:");
        alertService.fireService(address).forEach(as->log.info(as.toString()));
        return alertService.fireService(address);
    }

    @GetMapping("/flood/stations")
    public List<FloodDTO> getFloodStation(@RequestParam("stations") String stations){
        log.info("Request:GET /flood/stations?stations="+ stations);
        log.info("Response:");
        alertService.personOfStationService(stations).forEach(as->log.info(as.toString()));
        return alertService.personOfStationService(stations);
    }

    @GetMapping("/personInfo")
    public List<PersonInfoDTO> getPersoninfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        log.info("Request:GET /personInfo?firstName="+ firstName + "&lastName="+lastName);
        log.info("Response:");
        alertService.personWithMedicalRecordService(firstName,lastName).forEach(as->log.info(as.toString()));
        return alertService.personWithMedicalRecordService(firstName,lastName);
    }

    @GetMapping("/communityEmail")
    public Set<String> getCommunityEmail(@RequestParam("city") String city){
        log.info("Request:GET /communityEmail?city="+ city);
        log.info("Response:");
        alertService.emailPerCityService(city).forEach(as->log.info(as.toString()));
        return alertService.emailPerCityService(city);
    }
}
