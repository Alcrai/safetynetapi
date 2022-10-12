package com.safetynetapi.service;

import com.safetynetapi.dto.FireStationDTO;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationService implements IFireStationService{
    private ILoadDAO loadData;

    @Autowired
    public FireStationService(ILoadDAO loadData) {
       this.loadData = loadData;
    }

    @Override
    public List<FireStationDTO> personOfStationService(String station_number){
        List<FireStation> listFireStation = loadData.findAllFireStation();
        List<Person> listPerson = loadData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = loadData.findAllMedicalRecord();
        List<FireStationDTO> result = new ArrayList<>();
        final int[] countAdult = {0};
        final int[] countChildren = {0};
        listFireStation.forEach(lf -> {
            if(lf.getStation().equals(station_number)) {
                String address = lf.getAddress();
                listPerson.forEach(p -> {
                    if (p.getAddress().equals(address)) {
                        result.add(new FireStationDTO(p.getFirstName(),p.getLastname(),p.getAddress(), p.getCity(),p.getPhone()));
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
        result.add(new FireStationDTO( "","","","","",countAdult[0], countChildren[0]));
        return result;
    }

    @Override
    public FireStation save(FireStation fireStation) {
        loadData.saveFireStation(fireStation);
        return fireStation;
    }

    @Override
    public List<FireStation> fireStationList() {
        return loadData.findAllFireStation();
    }

    @Override
    public FireStation update(String address, String station) {
        return loadData.updateFireStation(address,station);
    }

    @Override
    public FireStation delete(String address, String station) {
        return loadData.deleteFireStation(address,station);
    }
}
