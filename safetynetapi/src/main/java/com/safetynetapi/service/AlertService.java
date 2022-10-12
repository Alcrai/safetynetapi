package com.safetynetapi.service;

import com.safetynetapi.dto.ChildAlertDTO;
import com.safetynetapi.dto.FireDTO;
import com.safetynetapi.dto.FloodDTO;
import com.safetynetapi.dto.PersonInfoDTO;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlertService implements IAlertService{
    public ILoadDAO loadData;
    @Autowired
    public AlertService(ILoadDAO loadData) {
        this.loadData =loadData;
    }
    @Override
    public List<ChildAlertDTO> childAlertService(String address) {
        List<Person> listPerson = loadData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = loadData.findAllMedicalRecord();
        List<ChildAlertDTO> result = new ArrayList<>();
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
                            result.add(new ChildAlertDTO(lmr.getFirstName(),lmr.getLastName(),lmr.getAge(), searchfamily));

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
       /* List<String> address = listFireStation.stream()
                .filter(e->e.getStation().equals(fireStationNumber))
                .map(e->e.getAddress())
                .collect(Collectors.toList());
        result =  listPerson.stream()
                .filter(e->e.getAddress().equals(address.stream()))
                .map(e-> e.getPhone())
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
    public List<FireDTO> fireService(String address){
        List<Person> listPerson =   loadData.findAllPerson();
        List<FireStation> listFireStation= loadData.findAllFireStation();
        List<MedicalRecord> listmedicalRecord = loadData.findAllMedicalRecord();
        List<FireDTO> result=new ArrayList<>();
        listPerson.forEach(lp->{
            if (lp.getAddress().equals(address)){
                listmedicalRecord.forEach(lmr->{
                    if (lmr.getLastName().equals(lp.getLastname()) && lmr.getFirstName().equals(lp.getFirstName())){
                        listFireStation.forEach(lf->{
                            if (lf.getAddress().equals(address)){
                                result.add(new FireDTO(lmr.getFirstName(),lmr.getLastName(), lp.getAddress(),
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
    public List<FloodDTO> personOfStationService(String stations){
        List<Person> listPerson =   loadData.findAllPerson();
        List<FireStation> listFireStation= loadData.findAllFireStation();
        List<MedicalRecord> listMedicalRecord = loadData.findAllMedicalRecord();
        List<FloodDTO> result=new ArrayList<>();
        listFireStation.forEach(lf->{
            if (lf.getStation().equals(stations)){
                listPerson.forEach(lp->{
                    if (lp.getAddress().equals(lf.getAddress())){
                        listMedicalRecord.forEach(lmr->{
                            if(lmr.getLastName().equals(lp.getLastname()) && lmr.getFirstName().equals(lp.getFirstName())){
                                result.add(new FloodDTO(lf.getAddress(), lf.getStation(), lmr.getFirstName(),lmr.getLastName(),lp.getPhone(),
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
    public List<PersonInfoDTO> personWithMedicalRecordService(String firstName, String lastName){
        List<Person> listPerson =   loadData.findAllPerson();
        List<MedicalRecord> listMedicalRecord = loadData.findAllMedicalRecord();
        List<PersonInfoDTO> result=new ArrayList<>();
        listPerson.forEach(lp->{
            if (lp.getLastname().equals(lastName) && lp.getFirstName().equals(firstName)) {
                listMedicalRecord.forEach(lmr->{
                    if(lp.getLastname().equals(lmr.getLastName()) && lp.getFirstName().equals(lmr.getFirstName())){
                        result.add(new PersonInfoDTO(lp.getFirstName(),lp.getLastname(),lp.getAddress(),
                                lp.getCity(), lp.getEmail(), lmr.getAge(), lmr.getMedications(), lmr.getAllergies()));
                    }
                });
            }
        });
        return result;
    }

    @Override
    public Set<String> emailPerCityService(String city){
        List<Person> listPerson = loadData.findAllPerson();
        return listPerson.stream()
                .filter(lp->lp.getCity().equals(city))
                .map(lp->lp.getEmail())
                .collect(Collectors.toSet());
    }
}
