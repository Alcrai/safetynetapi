package com.safetynetapi.repository;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Component

public class LoadingDataJson implements ILoadingData {
    private List<Person> persons ;
    private List<FireStation> fireStations;
    private List<MedicalRecord> medicalRecords;

    private String filePath ;

    private static final Logger logger = LogManager.getLogger("LoadingDataJson");
    @Autowired
    public LoadingDataJson(@Value("${file.path}") String filePath) {
        this.filePath = filePath;
        this.persons = this.findAllPerson();
        this.fireStations = this.findAllFireStation();
        this.medicalRecords = this.findAllMedicalRecord();
    }

    public List<Person> findAllPerson()  {

        byte[] bytesFile = new byte[0];
        Any any = null;

        try {
            bytesFile = Files.readAllBytes(new File(filePath).toPath());
            JsonIterator iter = JsonIterator.parse(bytesFile);
            any = iter.readAny();
            logger.info("Create List Of Person");
        } catch (IOException e) {
            logger.error("Error the file is Empty");
        }
        Any personAny = any.get("persons");
        persons = new ArrayList<>();
        personAny.forEach(a -> persons.add(new Person(a.get("firstName").toString(),
                a.get("lastName").toString(),
                a.get("address").toString(),
                a.get("city").toString(),
                a.get("zip").toString(),
                a.get("phone").toString(),
                a.get("email").toString())));

        return persons;
    }

    public List<FireStation> findAllFireStation() {
        byte[] bytesFile = new byte[0];
        Any any = null;

        try {
            bytesFile = Files.readAllBytes(new File(filePath).toPath());
            JsonIterator iter = JsonIterator.parse(bytesFile);
            any = iter.readAny();
            logger.info("Create List Of FireStation");
        } catch (IOException e) {
            logger.error("Error the file is Empty");
        }
        Any fireStationAny = any.get("firestations");
        fireStations = new ArrayList<>();
        fireStationAny.forEach(a -> fireStations.add(new FireStation(a.get("address").toString(),
                a.get("station").toString())));

        return fireStations;
    }

    public List<MedicalRecord> findAllMedicalRecord() {
        byte[] bytesFile = new byte[0];
        Any any = null;

        try {
            bytesFile = Files.readAllBytes(new File(filePath).toPath());
            JsonIterator iter = JsonIterator.parse(bytesFile);
            any = iter.readAny();
            logger.info("Create List Of Medical Record");
        } catch (IOException e) {
            logger.error("Error the file is Empty");
        }
        Any medicalRecordAny = any.get("medicalrecords");
        medicalRecords = new ArrayList<>();
        medicalRecordAny.forEach(m->
                medicalRecords.add(new MedicalRecord(
                        m.get("firstName").toString(),
                        m.get("lastName").toString(),
                        m.get("birthdate").toString(),
                        m.get("medications").toString(),
                        m.get("allergies").toString())));

        return medicalRecords;
    }

    @Override
    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public List<FireStation> getFireStations() {
        return fireStations;
    }

    @Override
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
}



