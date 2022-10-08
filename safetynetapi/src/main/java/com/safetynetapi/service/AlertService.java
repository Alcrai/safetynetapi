package com.safetynetapi.service;

import com.safetynetapi.dto.ChildAlertDto;
import com.safetynetapi.dto.FireDto;
import com.safetynetapi.dto.FloodDto;
import com.safetynetapi.dto.PersonInfoDto;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadData;
import com.safetynetapi.repository.ILoadingData;
import com.safetynetapi.repository.LoadData;
import com.safetynetapi.repository.LoadingDataJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlertService implements IAlertService{
    private ILoadingData loadingData;
    private ILoadData loadData;
    @Autowired
    public AlertService(ILoadData loadData) {
        loadingData = new LoadingDataJson();
        loadData = new LoadData(loadingData.getPersons(),loadingData.getFireStations(),loadingData.getMedicalRecords());
        this.loadData = loadData;
    }
    @Override
    public List<ChildAlertDto> childAlertService(String address) {
        List<Person> listPerson = loadData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = loadData.findAllMedicalRecord();
        List<ChildAlertDto> result = new ArrayList<>();
        List<String> searchfamily = new ArrayList<>();
        listPerson.forEach(lp -> {
            if (lp.getAddress().equals(address)) {
                searchfamily.add("firstName: " + lp.getFirstName() + " " +
                        "lastName: " + lp.getLastname());
            }
        });
        listPerson.forEach(lp -> {
            if (lp.getAddress().equals(address)) {
                listMedicalRecord.forEach(lmr -> {
                    if (lp.getLastname().equals(lmr.getLastName()) && lp.getFirstName().equals(lmr.getFirstName())) {
                        if (lmr.getAge() < 19) {
                            result.add(new ChildAlertDto(lmr.getFirstName(),lmr.getLastName(),lmr.getAge(), searchfamily));

                        }
                    }
                });
            }
        });
        return result;
    }

    @Override
    public Set<String> phoneAlertService(String fireStationNumber){
        List<Person> listPerson =   loadData.findAllPerson();
        List<FireStation> listFireStation= loadData.findAllFireStation();
        Set<String> result=new HashSet<String>();
       /* Set<String> address = listFireStation.stream()
                .filter(lf->lf.getStationNumber().equals(fireStationNumber))
                .map(lf->lf.getAddress())
                .collect(Collectors.toSet());
        result = listPerson.stream()
                .filter(lp->lp.getAddress().equals(address.stream().map(a->a)))
                .map(lp->lp.getPhone())
                .collect(Collectors.toSet());*/
        listFireStation.forEach(lf->{
            if (lf.getStation().equals(fireStationNumber)){
                listPerson.forEach(lp->{
                    if (lp.getAddress().equals(lf.getAddress())){
                        result.add("phone : "+ lp.getPhone());
                    }
                });
            }
        });
        return result;
    }

    @Override
    public List<FireDto> fireService(String address){
        List<Person> listPerson =   loadData.findAllPerson();
        List<FireStation> listFireStation= loadData.findAllFireStation();
        List<MedicalRecord> listmedicalRecord = loadData.findAllMedicalRecord();
        List<FireDto> result=new ArrayList<>();
        listPerson.forEach(lp->{
            if (lp.getAddress().equals(address)){
                listmedicalRecord.forEach(lmr->{
                    if (lmr.getLastName().equals(lp.getLastname()) && lmr.getFirstName().equals(lp.getFirstName())){
                        listFireStation.forEach(lf->{
                            if (lf.getAddress().equals(address)){
                                result.add(new FireDto(lmr.getFirstName(),lmr.getLastName(), lp.getAddress(),
                                        lp.getCity(), lf.getStation(),lp.getPhone(), lmr.getAge(),
                                        lmr.getMedications(), lmr.getAllergies()));
                            }
                        });
                    }
                });
            }
        });
        return result;
    }

    @Override
    public List<FloodDto> personOfStationService(String stations){
        List<Person> listPerson =   loadData.findAllPerson();
        List<FireStation> listFireStation= loadData.findAllFireStation();
        List<MedicalRecord> listMedicalRecord = loadData.findAllMedicalRecord();
        List<FloodDto> result=new ArrayList<>();
        listFireStation.forEach(lf->{
            if (lf.getStation().equals(stations)){
                listPerson.forEach(lp->{
                    if (lp.getAddress().equals(lf.getAddress())){
                        listMedicalRecord.forEach(lmr->{
                            if(lmr.getLastName().equals(lp.getLastname()) && lmr.getFirstName().equals(lp.getFirstName())){
                                result.add(new FloodDto(lf.getAddress(), lf.getStation(), lmr.getFirstName(),lmr.getLastName(),lp.getPhone(),
                                        lmr.getAge(),lmr.getMedications(),lmr.getAllergies()));
                            }
                        });
                    }
                });
            }
        });
        return result;
    }

    @Override
    public List<PersonInfoDto> personWithMedicalRecordService(String firstName, String lastName){
        List<Person> listPerson =   loadData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = loadData.findAllMedicalRecord();
        List<PersonInfoDto> result=new ArrayList<>();
        listPerson.forEach(lp->{
            if (lp.getLastname().equals(lastName) && lp.getFirstName().equals(firstName)) {
                listMedicalRecord.forEach(lmr->{
                    if(lp.getLastname().equals(lmr.getLastName()) && lp.getFirstName().equals(lmr.getFirstName())){
                        result.add(new PersonInfoDto(lp.getFirstName(),lp.getLastname(),lp.getAddress(),
                                lp.getCity(), lp.getEmail(), lmr.getAge(), lmr.getMedications(), lmr.getAllergies()));
                    }
                });
            }
        });
        return result;
    }

    @Override
    public Set<String> EmailPerCityService(String city){
        List<Person> listPerson = loadData.findAllPerson();
        return listPerson.stream()
                .filter(lp->lp.getCity().equals(city))
                .map(lp->lp.getEmail())
                .collect(Collectors.toSet());
    }
}
