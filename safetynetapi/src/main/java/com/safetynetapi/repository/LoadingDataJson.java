package com.safetynetapi.repository;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LoadingDataJson implements LoadingData{
    private static List<Person> persons ;
    private List<FireStation> fireStations;
    private List<MedicalRecord> medicalRecords;

    private String filePath = "safetynetapi/src/main/resources/data.json";

    @Override
    public List<Person> findAllPerson()  {

        byte[] bytesFile = new byte[0];
        try {
            bytesFile = Files.readAllBytes(new File(filePath).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = null;
        try {
            any = iter.readAny();
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    @Override
    public List<FireStation> findAllFireStation() {
        byte[] bytesFile = new byte[0];
        try {
            bytesFile = Files.readAllBytes(new File(filePath).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = null;
        try {
            any = iter.readAny();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Any fireStationAny = any.get("firestations");
        fireStations = new ArrayList<>();
        fireStationAny.forEach(a -> fireStations.add(new FireStation(a.get("address").toString(),
                a.get("station").toString())));

        return fireStations;
    }

    @Override
    public List<MedicalRecord> findAllMedicalRecord() {
        byte[] bytesFile = new byte[0];
        try {
            bytesFile = Files.readAllBytes(new File(filePath).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = null;
        try {
            any = iter.readAny();
        } catch (IOException e) {
            throw new RuntimeException(e);
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

 }


