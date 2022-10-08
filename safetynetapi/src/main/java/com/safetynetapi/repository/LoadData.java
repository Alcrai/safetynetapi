package com.safetynetapi.repository;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class LoadData implements ILoadData{
    public List<Person> persons;
    public List<FireStation> fireStations;
    public List<MedicalRecord> medicalRecords;

    public LoadData(List<Person> persons, List<FireStation> fireStations, List<MedicalRecord> medicalRecords) {
        this.persons = persons;
        this.fireStations = fireStations;
        this.medicalRecords = medicalRecords;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<FireStation> getFireStations() {
        return fireStations;
    }

    public void setFireStations(List<FireStation> fireStations) {
        this.fireStations = fireStations;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    @Override
    public List<Person> findAllPerson() {
        return persons;
    }

    @Override
    public List<FireStation> findAllFireStation() {
        return fireStations;
    }

    @Override
    public List<MedicalRecord> findAllMedicalRecord() {
        return medicalRecords;
    }

    @Override
    public FireStation save(FireStation fireStation){
        fireStations.add(fireStation);
        return fireStation;
    }

    @Override
    public FireStation updateFireStation(String address, String station) {
        FireStation fireStation = new FireStation(address, station);
        fireStations.forEach(fs->{
            if(fs.getAddress().equals(address)){
                fs.setStation(station);
            }
        });
        return fireStation;
    }

    @Override
    public FireStation deleteFireStation(String address, String station) {
       List<FireStation> updateFireStation =new ArrayList<>();
       if(!address.isEmpty()){
               fireStations.forEach(lf->{
                   if(!lf.getAddress().equals(address)){
                       updateFireStation.add(lf);
                   }
               });
           }
        if(!station.isEmpty()){
            fireStations.forEach(lf->{
                if(!lf.getStation().equals(station)){
                    updateFireStation.add(lf);
                }
            });
        }
        this.fireStations = updateFireStation;
        return new FireStation(address, station);
    }

    @Override
    public Person savePerson(Person person) {
        persons.add(person);
        return person;
    }

    @Override
    public Person updatePerson(String firstName, String lastName, Person person) {
        persons.forEach(p->{
            if(p.getFirstName().equals(firstName) && p.getLastname().equals(lastName)){
                p.setAddress(person.getAddress());
                p.setCity(person.getCity());
                p.setZip(person.getZip());
                p.setPhone(person.getPhone());
                p.setEmail(person.getEmail());
            }
        });
        return person;
    }

    @Override
    public Person deletePerson(String firstName, String lastName) {
        final Person[] result = {new Person()};
        List<Person> updatePerson= new ArrayList<>();
        persons.forEach(p->{
            if(p.getFirstName().equals(firstName)&& p.getLastname().equals(lastName)){
                result[0] = p;
            }else{
                updatePerson.add(p);
            }

        });
        this.persons=updatePerson;
        return result[0];
    }

    @Override
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
        return medicalRecord;
    }

    @Override
    public MedicalRecord updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord) {
        medicalRecords.forEach(mr->{
            if(mr.getFirstName().equals(firstName) && mr.getLastName().equals(lastName)){
                mr.setBirthdate(medicalRecord.getBirthdate());
                mr.setMedications(medicalRecord.getMedications());
                mr.setAllergies(medicalRecord.getAllergies());
            }
        });
        return medicalRecord;
    }

    @Override
    public MedicalRecord deleteMedicalRecord(String firstName, String lastName) {
        final MedicalRecord[] result = {new MedicalRecord()};
        List<MedicalRecord> updateMedicalRecord= new ArrayList<>();
        medicalRecords.forEach(mr->{
            if(mr.getFirstName().equals(firstName)&& mr.getLastName().equals(lastName)){
                result[0] = mr;
            }else{
                updateMedicalRecord.add(mr);
            }

        });
        this.medicalRecords=updateMedicalRecord;
        return result[0];
    }
}
