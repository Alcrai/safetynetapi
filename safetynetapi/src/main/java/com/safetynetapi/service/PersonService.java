package com.safetynetapi.service;

import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadData;
import com.safetynetapi.repository.ILoadingData;
import com.safetynetapi.repository.LoadData;
import com.safetynetapi.repository.LoadingDataJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonService implements IPersonService{
   private ILoadData loadData;
    @Autowired
    public PersonService(ILoadData loadData) {
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
