package com.safetynetapi.service;

import com.safetynetapi.model.Person;

import java.util.List;

public interface IPersonService {

    List<Person> findAllPerson();

    Person save(Person person);

    Person updatePerson(String firstName, String lastName, Person person);

    Person deletePerson(String firstName, String lastName);
}
