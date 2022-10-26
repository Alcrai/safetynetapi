package com.safetynetapi.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ChildAlertDTO {
    private String firstName;
    private String lastName;
    private int age;
    private List<PersonDTO> family;

    public ChildAlertDTO(String firstName, String lastName, int age, List<PersonDTO> family) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.family = new ArrayList<PersonDTO>(family);
    }

    @Override
    public String toString() {
        return "ChildAlertDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", family=" + family +
                '}';
    }
}
