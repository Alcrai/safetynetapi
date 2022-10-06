package com.safetynetapi.service;

import com.safetynetapi.dto.FireStationDto;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationService implements IFireStationService{
    private ILoadingData iLoadingData;

    @Autowired
    public FireStationService(ILoadingData iLoadingData) {
        this.iLoadingData = iLoadingData;
    }

    @Override
    public List<FireStationDto> personOfStationService(String station_number){
        List<FireStation> listFireStation = iLoadingData.findAllFireStation();
        List<Person> listPerson = iLoadingData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = iLoadingData.findAllMedicalRecord();
        List<FireStationDto> result = new ArrayList<>();
        final int[] countAdult = {0};
        final int[] countChildren = {0};
        listFireStation.forEach(lf -> {
            if(lf.getStationNumber().equals(station_number)) {
                String address = lf.getAddress();
                listPerson.forEach(p -> {
                    if (p.getAddress().equals(address)) {
                        result.add(new FireStationDto(p.getFirstName(),p.getLastname(),p.getAddress(), p.getCity(),p.getPhone()));
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
        result.add(new FireStationDto( "","","","","",countAdult[0], countChildren[0]));
        return result;
    }
}
