package com.safetynetapi.model.dto;

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

    @Override
    public String toString() {
        return "FloodDTO{" +
                "address='" + address + '\'' +
                ", station='" + station + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", medications='" + medications + '\'' +
                ", allergies='" + allergies + '\'' +
                '}';
    }
}
