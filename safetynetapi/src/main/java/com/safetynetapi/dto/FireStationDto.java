package com.safetynetapi.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.context.annotation.Bean;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FireStationDto {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String phone;
    private int numberOfAdult;
    private int numberOfChildren;

    public FireStationDto(String firstName, String lastName, String address, String city, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }

    public FireStationDto(String firstName, String lastName, String address, String city, String phone, int numberOfAdult, int numberOfChildren) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.numberOfAdult = numberOfAdult;
        this.numberOfChildren = numberOfChildren;
    }
}


