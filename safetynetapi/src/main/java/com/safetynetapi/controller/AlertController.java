package com.safetynetapi.controller;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.LoadingData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AlertController {
    private final LoadingData loadingData;

    public AlertController(LoadingData loadingData) {
        this.loadingData = loadingData;
    }

    @GetMapping("/childAlert")
    public List<String> listChildAlert(@RequestParam("address") String address){
        List<Person> listPerson = loadingData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = loadingData.findAllMedicalRecord();
        List<String> result = new ArrayList<>();
        result.add("Children List leave in :" + address);
        List<String> family = new ArrayList<>();
        listPerson.forEach(lp->{
            if (lp.getAddress().equals(address)){
                family.add("firstName: " + lp.getFirstName()+" "+
                        "lastName: " +  lp.getLastname());
            }
        });
        listPerson.forEach(lp->{
            if(lp.getAddress().equals(address)){
                listMedicalRecord.forEach(lmr->{
                    if(lp.getLastname().equals(lmr.getLastName())&& lp.getFirstName().equals(lmr.getFirstName())){
                        if(lmr.getAge()<19){

                            result.add("firstName: " + lmr.getFirstName() + " "+
                                    "lastName: " + lmr.getLastName() + " "+
                                    "age: "+ lmr.getAge() + " "+
                                    "family: ");
                            family.forEach(f->result.add(f));
                        }
                    }
                });
            }
        });

        return result;
    }

    @GetMapping("/phoneAlert")
    public List<String> ListPhoneAlert(@RequestParam("firestation") String fireStationNumber){
        List<Person> listPerson =   loadingData.findAllPerson();
        List<FireStation> listFireStation= loadingData.findAllFireStation();
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

    @GetMapping("/fire")
    public List<String> listFire(@RequestParam("address") String address){
        List<Person> listPerson =   loadingData.findAllPerson();
        List<FireStation> listFireStation= loadingData.findAllFireStation();
        List<MedicalRecord> listmedicalRecord = loadingData.findAllMedicalRecord();
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

    @GetMapping("/flood/stations")
    public List<String> listPersonOfStation(@RequestParam("stations") String stations){
        List<Person> listPerson =   loadingData.findAllPerson();
        List<FireStation> listFireStation= loadingData.findAllFireStation();
        List<MedicalRecord> listMedicalRecord = loadingData.findAllMedicalRecord();
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

    @GetMapping("/personInfo")
    public List<String> listPersonWithMedicalRecord(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName){
        List<Person> listPerson =   loadingData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = loadingData.findAllMedicalRecord();
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

    @GetMapping("/communityEmail")
    public List<String> ListEmailPerCity(@RequestParam("city") String city){
        List<Person> listPerson = loadingData.findAllPerson();
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
