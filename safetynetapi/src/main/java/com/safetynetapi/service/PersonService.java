package com.safetynetapi.service;

import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadData;
import com.safetynetapi.repository.ILoadingData;
import com.safetynetapi.repository.LoadData;
import com.safetynetapi.repository.LoadingDataJson;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonService implements IPersonService{
    private ILoadingData loadingData;
    private ILoadData loadData;

    public PersonService(ILoadData loadData) {
        loadingData = new LoadingDataJson();
        loadData = new LoadData(loadingData.getPersons(),loadingData.getFireStations(),loadingData.getMedicalRecords());
        this.loadData = loadData;
    }
    @Override
    public Person updatePerson(String firstName, String lastName, Person person) {
        return loadData.updatePerson(firstName,lastName,person);
    }

    @Override
    public Person deletePerson(String firstName, String lastName) {
        return loadData.deletePerson(firstName,lastName);
    }


    @Override
    public List<Person> findAllPerson() {
        return loadData.findAllPerson();
    }

    @Override
    public Person save(Person person) {
        return loadData.savePerson(person);
    }
}
