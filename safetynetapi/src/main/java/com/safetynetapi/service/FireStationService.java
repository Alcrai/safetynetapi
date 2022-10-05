package com.safetynetapi.service;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadingData;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationService {
    private ILoadingData iLoadingData;

    public FireStationService(ILoadingData iLoadingData) {
        this.iLoadingData = iLoadingData;
    }

    public List<String> personOfStationService(String station_number){
        List<FireStation> listFireStation = iLoadingData.findAllFireStation();
        List<Person> listPerson = iLoadingData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = iLoadingData.findAllMedicalRecord();
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
