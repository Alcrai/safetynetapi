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
public class AlertService {

    private ILoadingData iLoadingData;

    public AlertService(ILoadingData iLoadingData) {
        this.iLoadingData = iLoadingData;
    }

    public List<String> childAlertService(String address) {
        List<Person> listPerson = iLoadingData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = iLoadingData.findAllMedicalRecord();
        List<String> result = new ArrayList<>();
        result.add("Children List leave in :" + address);
        List<String> family = new ArrayList<>();
        listPerson.forEach(lp -> {
            if (lp.getAddress().equals(address)) {
                family.add("firstName: " + lp.getFirstName() + " " +
                        "lastName: " + lp.getLastname());
            }
        });
        listPerson.forEach(lp -> {
            if (lp.getAddress().equals(address)) {
                listMedicalRecord.forEach(lmr -> {
                    if (lp.getLastname().equals(lmr.getLastName()) && lp.getFirstName().equals(lmr.getFirstName())) {
                        if (lmr.getAge() < 19) {

                            result.add("firstName: " + lmr.getFirstName() + " " +
                                    "lastName: " + lmr.getLastName() + " " +
                                    "age: " + lmr.getAge() + " " +
                                    "family: ");
                            family.forEach(f -> result.add(f));
                        }
                    }
                });
            }
        });

        return result;
    }

    public List<String> phoneAlertService(String fireStationNumber){
        List<Person> listPerson =   iLoadingData.findAllPerson();
        List<FireStation> listFireStation= iLoadingData.findAllFireStation();
        List<String> result=new ArrayList<>();
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

    public List<String> fireService(String address){
        List<Person> listPerson =   iLoadingData.findAllPerson();
        List<FireStation> listFireStation= iLoadingData.findAllFireStation();
        List<MedicalRecord> listmedicalRecord = iLoadingData.findAllMedicalRecord();
        List<String> result=new ArrayList<>();
        result.add("Liste Person with medical records leave in : " + address);
        listFireStation.forEach(lf->{
            if (lf.getAddress().equals(address)){
                result.add("station : " + lf.getStationNumber());
            }
        });
        listPerson.forEach(lp->{
            if (lp.getAddress().equals(address)){
                listmedicalRecord.forEach(lmr->{
                    if (lmr.getLastName().equals(lp.getLastname()) && lmr.getFirstName().equals(lp.getFirstName())){
                        result.add("firstName: " + lmr.getFirstName() + " " +
                                "LastName: " + lmr.getLastName() + " " +
                                "phone: " + lp.getPhone() + " " +
                                "age: " + lmr.getAge() + " " +
                                "medications: " + lmr.getMedications() + " " +
                                "allergies: " + lmr.getAllergies());
                    }
                });
            }
        });
        return result;
    }

    public List<String> personOfStationService(String stations){
        List<Person> listPerson =   iLoadingData.findAllPerson();
        List<FireStation> listFireStation= iLoadingData.findAllFireStation();
        List<MedicalRecord> listMedicalRecord = iLoadingData.findAllMedicalRecord();
        List<String> result=new ArrayList<>();
        result.add("List Person with medical records depends on the station : " + stations);
        listFireStation.forEach(lf->{
            if (lf.getStationNumber().equals(stations)){
                result.add("address: "+lf.getAddress());
                listPerson.forEach(lp->{
                    if (lp.getAddress().equals(lf.getAddress())){
                        listMedicalRecord.forEach(lmr->{
                            if(lmr.getLastName().equals(lp.getLastname()) && lmr.getFirstName().equals(lp.getFirstName())){
                                result.add("firstName: " + lmr.getFirstName() + " " +
                                        "LastName: " + lmr.getLastName() + " " +
                                        "phone: " + lp.getPhone() + " " +
                                        "age: " + lmr.getAge() + " " +
                                        "medications: " + lmr.getMedications() + " " +
                                        "allergies: " + lmr.getAllergies());
                            }
                        });
                    }
                });
            }
        });
        return result;
    }
    public List<String> personWithMedicalRecordService(String firstName,String lastName){
        List<Person> listPerson =   iLoadingData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = iLoadingData.findAllMedicalRecord();
        List<String> result=new ArrayList<>();
        result.add("Info : " + firstName + " " + lastName);
        listPerson.forEach(lp->{
            if (lp.getLastname().equals(lastName) && lp.getFirstName().equals(firstName)) {
                listMedicalRecord.forEach(lmr->{
                    if(lp.getLastname().equals(lmr.getLastName()) && lp.getFirstName().equals(lmr.getFirstName())){
                        result.add("firstName: " + lp.getFirstName() + " "+
                                "LastName: " + lp.getLastname() + " "+
                                "Age: " + lmr.getAge() + " "+
                                "mail: " + lp.getEmail() + " "+
                                "medications: " + lmr.getMedications() + " "+
                                "allergies: " + lmr.getAllergies());
                    }
                });

            }
        });
        return result;
    }
    public List<String> EmailPerCityService(String city){
        List<Person> listPerson = iLoadingData.findAllPerson();
        List<String> result = new ArrayList<>();
        result.add("List Of Email people leave in " + city);
        listPerson.forEach(lp->{
            if(lp.getCity().equals(city)){
                result.add("email: " + lp.getEmail());
            }
        });
        return result;
    }
}
