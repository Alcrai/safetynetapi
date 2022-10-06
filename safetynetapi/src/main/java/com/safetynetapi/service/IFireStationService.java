package com.safetynetapi.service;

import com.safetynetapi.dto.FireStationDto;

import java.util.List;

public interface IFireStationService {

    List<FireStationDto> personOfStationService(String station_number);
}
