package com.safetynetapi.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FloodDTO {
    String address;
    String station;
    String firstName;
    String lastName;
    String phone;
    int age;
    String medications;
    String allergies;

    public FloodDTO(String address, String station, String firstName, String lastName, String phone, int age, String medications, String allergies) {
        this.address = address;
        this.station = station;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }
}
