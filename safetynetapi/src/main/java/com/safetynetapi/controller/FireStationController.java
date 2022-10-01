package com.safetynetapi.controller;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.LoadingData;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FireStationController {

    private final LoadingData loadingData;

    public FireStationController(LoadingData loadingData) {
        this.loadingData = loadingData;
    }

 /*  @GetMapping("/firestation")
    public List<FireStation> listAllFireStation() {
        return loadingData.findAllFireStation();
    }*/


    @GetMapping("/firestation")
    public List<String> listOfPersonOfStation(@RequestParam("stationNumber") String station_number){
        List<FireStation> listFireStation = loadingData.findAllFireStation();
        List<Person> listPerson = loadingData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = loadingData.findAllMedicalRecord();
        List<String> result = new ArrayList<>();
        final int[] countAdult = {0};
        final int[] countChildren = {0};
        listFireStation.forEach(lf -> {
            if(lf.getStationNumber().equals(station_number)) {
                String address = lf.getAddress();
                listPerson.forEach(p -> {
                    if (p.getAddress().equals(address)) {
                        result.add("FirstName : " + p.getFirstName() +
                                " LastName : " + p.getLastname() +
                                " address : " + p.getAddress() +
                                " city : " + p.getCity() +
                                " phone : " + p.getPhone());
                        System.out.println(p.toString());
                        listMedicalRecord.forEach(lmr->{
                            if(lmr.getFirstName().equals(p.getFirstName()) && lmr.getLastName().equals(p.getLastname())){
                                if (lmr.getAge()>18){
                                    countAdult[0]++;
                                }else {
                                    countChildren[0]++;
                                }
                            }
                        });
                    }
                });
            }
        });
        result.add(" Number of Adult : "+ countAdult[0] + " Number Of Children :" + countChildren[0]);
        return result;
    }
}
