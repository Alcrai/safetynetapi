package com.safetynetapi.controller;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadingData;
import com.safetynetapi.service.AlertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AlertController {
    private  ILoadingData iLoadingData;
    private AlertService alertService;

    public AlertController(ILoadingData iLoadingData, AlertService alertService) {
        this.iLoadingData = iLoadingData;
        this.alertService = alertService;
    }

    @GetMapping("/childAlert")
    public List<String> listChildAlert(@RequestParam("address") String address){
      alertService = new AlertService(iLoadingData);
      return alertService.childAlertService(address);
    }

    @GetMapping("/phoneAlert")
    public List<String> ListPhoneAlert(@RequestParam("firestation") String fireStationNumber){
        alertService = new AlertService(iLoadingData);
        return alertService.phoneAlertService(fireStationNumber);
    }

    @GetMapping("/fire")
    public List<String> listFire(@RequestParam("address") String address){
        alertService = new AlertService(iLoadingData);
        return alertService.fireService(address);
    }

    @GetMapping("/flood/stations")
    public List<String> listPersonOfStation(@RequestParam("stations") String stations){
        alertService = new AlertService(iLoadingData);
        return alertService.personOfStationService(stations);
    }

    @GetMapping("/personInfo")
    public List<String> listPersonWithMedicalRecord(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName){
        alertService = new AlertService(iLoadingData);
        return alertService.personWithMedicalRecordService(firstName,lastName);
    }

    @GetMapping("/communityEmail")
    public List<String> ListEmailPerCity(@RequestParam("city") String city){
        alertService = new AlertService(iLoadingData);
        return alertService.EmailPerCityService(city);
    }
}
