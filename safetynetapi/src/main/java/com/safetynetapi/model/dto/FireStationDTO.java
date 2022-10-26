package com.safetynetapi.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FireStationDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String phone;
    private int numberOfAdult;
    private int numberOfChildren;

    public FireStationDTO(String firstName, String lastName, String address, String city, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }

    public FireStationDTO(String firstName, String lastName, String address, String city, String phone, int numberOfAdult, int numberOfChildren) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.numberOfAdult = numberOfAdult;
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public String toString() {
        return "FireStationDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", numberOfAdult=" + numberOfAdult +
                ", numberOfChildren=" + numberOfChildren +
                '}';
    }
}


