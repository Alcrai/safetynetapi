package com.safetynetapi.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FireStation {
    private String address;
    private  String stationNumber;

    public FireStation(String address, String stationNumber) {
        this.address = address;
        this.stationNumber = stationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    @Override
    public String toString() {
        return "FireStation{" +
                "address='" + address + '\'' +
                ", stationNumber='" + stationNumber + '\'' +
                '}';
    }
}
