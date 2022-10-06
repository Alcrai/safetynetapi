package com.safetynetapi.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.safetynetapi.dto.ChildAlertDto;
import com.safetynetapi.dto.FireDto;
import com.safetynetapi.dto.FloodDto;
import com.safetynetapi.dto.PersonInfoDto;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AlertService implements IAlertService{

    private ILoadingData iLoadingData;
    @Autowired
    public AlertService(ILoadingData iLoadingData) {
        this.iLoadingData = iLoadingData;
    }
    @Override
    public List<ChildAlertDto> childAlertService(String address) {
        List<Person> listPerson = iLoadingData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = iLoadingData.findAllMedicalRecord();
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
        List<Person> listPerson =   iLoadingData.findAllPerson();
        List<FireStation> listFireStation= iLoadingData.findAllFireStation();
        Set<String> result=new HashSet<String>();
        listFireStation.forEach(lf->{
            if (lf.getStationNumber().equals(fireStationNumber)){
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
        List<Person> listPerson =   iLoadingData.findAllPerson();
        List<FireStation> listFireStation= iLoadingData.findAllFireStation();
        List<MedicalRecord> listmedicalRecord = iLoadingData.findAllMedicalRecord();
        List<FireDto> result=new ArrayList<>();
        listPerson.forEach(lp->{
            if (lp.getAddress().equals(address)){
                listmedicalRecord.forEach(lmr->{
                    if (lmr.getLastName().equals(lp.getLastname()) && lmr.getFirstName().equals(lp.getFirstName())){
                        listFireStation.forEach(lf->{
                            if (lf.getAddress().equals(address)){
                                result.add(new FireDto(lmr.getFirstName(),lmr.getLastName(), lp.getAddress(),
                                        lp.getCity(), lf.getStationNumber(),lp.getPhone(), lmr.getAge(),
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
        List<Person> listPerson =   iLoadingData.findAllPerson();
        List<FireStation> listFireStation= iLoadingData.findAllFireStation();
        List<MedicalRecord> listMedicalRecord = iLoadingData.findAllMedicalRecord();
        List<FloodDto> result=new ArrayList<>();
        listFireStation.forEach(lf->{
            if (lf.getStationNumber().equals(stations)){
                listPerson.forEach(lp->{
                    if (lp.getAddress().equals(lf.getAddress())){
                        listMedicalRecord.forEach(lmr->{
                            if(lmr.getLastName().equals(lp.getLastname()) && lmr.getFirstName().equals(lp.getFirstName())){
                                result.add(new FloodDto(lf.getAddress(), lf.getStationNumber(), lmr.getFirstName(),lmr.getLastName(),lp.getPhone(),
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
        List<Person> listPerson =   iLoadingData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = iLoadingData.findAllMedicalRecord();
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
        List<Person> listPerson = iLoadingData.findAllPerson();
        Set<String> result = new HashSet<String>();
        listPerson.forEach(lp->{
            if(lp.getCity().equals(city)){
                result.add("email: " + lp.getEmail());
            }
        });
        return result;
    }
}
