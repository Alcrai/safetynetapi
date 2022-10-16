package com.safetynetapi.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FireDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String station;
    private String phone;
    private int age;
    private String medications;
    private String allergies;
    public FireDTO(String firstName, String lastName, String address, String city, String station, String phone, int age, String medications, String allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.station = station;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }
}
