package com.safetynetapi.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ChildAlertDto {
    private String firstName;
    private String lastname;
    private int age;
    private List<String> family;

    public ChildAlertDto(String firstName, String lastname, int age, List<String> family) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.age = age;
        this.family = new ArrayList<String>(family);
    }
}
