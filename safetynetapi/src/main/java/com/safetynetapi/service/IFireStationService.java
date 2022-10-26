package com.safetynetapi.service;

import com.safetynetapi.model.dto.FireStationDTO;
import com.safetynetapi.model.FireStation;

import java.util.List;

public interface IFireStationService {

    List<FireStationDTO> personOfStationService(String station_number);
    FireStation save(FireStation fireStation);

    List<FireStation> fireStationList();

    FireStation update(String address, String station);

    FireStation delete(String address, String station);
}
