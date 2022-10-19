package com.safetynetapi.repository;

import com.safetynetapi.model.FireStation;
import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoadDAOTest {
    @Mock
    private ILoadingData loadingData;
    private LoadDAO loadDAO;
    public List<Person> persons;
    public List<FireStation> fireStations;
    public List<MedicalRecord> medicalRecords;

    @BeforeEach
    void initTest(){

        persons=new ArrayList<>();
        fireStations=new ArrayList<>();
        medicalRecords=new ArrayList<>();
        persons.add(new Person("Alex","Blandio","1509 Culver St","Culver","97451","841-874-6512", "alexblandio@email.com"));
        persons.add(new Person("Cat","Blandio","1509 Culver St","Culver","97451","841-874-6512", "catblandio@email.com"));
        fireStations.add(new FireStation("1509 Culver St","1"));
        fireStations.add(new FireStation("25 Vosges St","2"));
        medicalRecords.add(new MedicalRecord("Alex","Blandio","03/06/2020", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]"));
        medicalRecords.add(new MedicalRecord("Cat","Blandio","03/06/1984", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]"));

        when(loadingData.getPersons()).thenReturn(persons);
        when(loadingData.getFireStations()).thenReturn(fireStations);
        when(loadingData.getMedicalRecords()).thenReturn(medicalRecords);
        loadDAO = new LoadDAO(loadingData);
    }

    @Test
    void findAllPersonReturnAList(){
        assertThat(loadDAO.findAllPerson()).hasSize(persons.size());
        /*verify(loadingData).getPersons();*/
    }

    @Test
    void findAllMedicalrecordReturnAList(){
        assertThat(loadDAO.findAllMedicalRecord()).hasSize(medicalRecords.size());
       /* verify(loadingData).getMedicalRecords();*/
    }

    @Test
    void findAllFireStationReturnAList(){
        assertThat(loadDAO.findAllFireStation()).hasSize(fireStations.size());
       /* verify(loadingData).getFireStations();*/
    }

    @Test
    void saveFireStationReturnFireStation(){
        FireStation fireStation = new FireStation("1 Culver St","6");
        assertThat(loadDAO.saveFireStation(fireStation)).isEqualTo(fireStation);
    }

    @Test
    void updateFireStationReturnFireStationAndUpdate() {
        FireStation fireStation = loadDAO.updateFireStation("1509 Culver St","5");
        List<String> station = fireStations.stream().filter(e->e.getAddress().equals("1509 Culver St")).map(FireStation::getStation).collect(Collectors.toList());
        assertThat(station).first().isEqualTo(fireStation.getStation());
    }

    @Test
    void deleteFireStation() {
        FireStation fireStation = loadDAO.deleteFireStation("25 Vosges St","2");
        assertThat(fireStations).hasSize(2);
    }

    @Test
    void savePersonReturnPersonAndSave(){
        Person person= new Person("Fleur","Blandio","1509 Culver St","Culver","97451","841-874-6512", "fleurblandio@email.com");
        assertThat(loadDAO.savePerson(person)).isEqualTo(person);
        /*List<Person> personList = persons.stream()
                .filter(e->e.getFirstName().equals("Fleur") && e.getFirstName().equals("Blandio"))
                .collect(Collectors.toList());
        assertThat(personList).first().isEqualTo(person);*/
        persons.forEach(p->{if(p.getFirstName().equals("Fleur")&&p.getLastName().equals("Blandio")){
            assertThat(p).isEqualTo(person);
        }});
    }

    @Test
    void updatePersonReturnPersonAndUpdate() {
        Person person= new Person("Fleur","Blandio","1509 Culver St","Culver","97451","123-123-123", "fleurblandio@email.com");
        assertThat(loadDAO.updatePerson("Fleur","Blandio",person)).isEqualTo(person);
        persons.forEach(p->{if(p.getFirstName().equals("Fleur")&&p.getLastName().equals("Blandio")){
            assertThat(p.getPhone()).isEqualTo(person.getPhone());
        }});

    }

    @Test
    void deletePersonReturnPerson() {
        loadDAO.deletePerson("Alex","Blandio");
        assertThat(persons).hasSize(2);
    }

    @Test
    void saveMedicalRecordReturnMedicalRecordAndSave(){
        MedicalRecord medicalRecord= new MedicalRecord("Fleur","Blandio","03/06/2020", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]");
        assertThat(loadDAO.saveMedicalRecord(medicalRecord)).isEqualTo(medicalRecord);
       medicalRecords.forEach(p->{if(p.getFirstName().equals("Fleur") && p.getLastName().equals("Blandio")){
            assertThat(p).isEqualTo(medicalRecord);
        }});
    }

    @Test
    void updateMedicalRecordReturnMedicalRecordAndUpdate() {
        MedicalRecord medicalRecord= new MedicalRecord("Fleur","Blandio","23/10/1978", "[\"aznol:350mg\", \"hydrapermazol:100mg\"]", "[\"nillacilan\"]");
        assertThat(loadDAO.updateMedicalRecord("Fleur","Blandio",medicalRecord)).isEqualTo(medicalRecord);
        medicalRecords.forEach(p->{if(p.getFirstName().equals("Fleur")&& p.getLastName().equals("Blandio")){
            assertThat(p.getBirthdate()).isEqualTo(medicalRecord.getBirthdate());
        }});

    }

    @Test
    void deleteMedicalRecord() {
        loadDAO.deleteMedicalRecord("Alex","Blandio");
        assertThat(medicalRecords).hasSize(2);
    }

}
