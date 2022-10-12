package com.safetynetapi.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PersonInfoDTO {
    private String firstname;
    private String lastName;
    private String address;
    private String city;
    private String mail;
    private int age;
    private String medications;
    private String allergies;

    public PersonInfoDTO(String firstname, String lastName, String address, String city, String mail, int age, String medications, String allergies) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.mail = mail;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }
}
