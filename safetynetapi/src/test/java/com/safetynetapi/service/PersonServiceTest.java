package com.safetynetapi.service;

import com.safetynetapi.model.Person;
import com.safetynetapi.repository.ILoadData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private ILoadData loadData;

    private PersonService personService;

    public List<Person> persons;

    @BeforeEach
    public void initTest(){
        personService = new PersonService(loadData);
        persons=new ArrayList<>();
        persons.add(new Person("Alex","Blandio","1509 Culver St","Culver","97451","841-874-6512", "alexblandio@email.com"));
        persons.add(new Person("Cat","Blandio","1509 Culver St","Culver","97451","841-874-6512", "catblandio@email.com"));
    }

    @Test
    public void findAllPersonReturnPersonList(){
        when(loadData.findAllPerson()).thenReturn(persons);
        assertThat(personService.findAllPerson()).size().isEqualTo(2);
        verify(loadData).findAllPerson();
    }

    @Test
    public void saveReturnPerson(){
        Person person = new Person("Alex","Bobo","1509 Culver St","Culver","97451","841-874-6512", "alexbobo@email.com");
        when(loadData.savePerson(person)).thenReturn(person);
        assertThat(personService.save(person)).isEqualTo(person);
        verify(loadData).savePerson(person);
    }

    @Test
    public void updatePersonReturnPerson(){
        Person person = new Person("Alex","Bobo","1509 Culver St","Culver","97451","841-874-6512", "alexbobo@email.com");
        when(loadData.updatePerson("Alex","bobo",person)).thenReturn(person);
        assertThat(personService.updatePerson("Alex","bobo",person)).isEqualTo(person);
        verify(loadData).updatePerson("Alex","bobo",person);
    }

    @Test
    public void deletePersonReturnPerson(){
        Person person = new Person("Alex","Bobo","1509 Culver St","Culver","97451","841-874-6512", "alexbobo@email.com");
        when(loadData.deletePerson("Alex","bobo")).thenReturn(person);
        assertThat(personService.deletePerson("Alex","bobo")).isEqualTo(person);
        verify(loadData).deletePerson("Alex","bobo");
    }

}
