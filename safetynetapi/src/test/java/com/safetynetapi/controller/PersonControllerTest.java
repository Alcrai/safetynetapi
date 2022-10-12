package com.safetynetapi.controller;

import com.safetynetapi.dto.FireDTO;
import com.safetynetapi.model.Person;
import com.safetynetapi.service.IAlertService;
import com.safetynetapi.service.IPersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @Mock
    private IPersonService personService;

    private PersonController personController;

    private List<Person> persons;
    private Person person;

    @BeforeEach
    public void init(){
        personController = new PersonController(personService);
        persons = new ArrayList<>();
        person = new Person("Alex","Blandio","1 Culver St","Culver","12563","123-123-123","alex@mail.com");
        persons.add(person);
    }

    @Test
    public void ListPersonsReturnAList(){
       when(personService.findAllPerson()).thenReturn(persons);
       assertThat(personController.listPersons()).size().isEqualTo(1);
       verify(personService).findAllPerson();
    }
    @Test
    public void deletePersonTestReturnMap(){
        when(personService.deletePerson("Alex","Blandio")).thenReturn(person);
        assertThat(personController.deletePerson("Alex","Blandio")).hasSize(1);
        verify(personService).deletePerson("Alex","Blandio");
    }

    



}
