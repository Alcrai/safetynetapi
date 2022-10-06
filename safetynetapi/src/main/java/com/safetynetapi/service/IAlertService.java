package com.safetynetapi.service;

import com.safetynetapi.dto.ChildAlertDto;
import com.safetynetapi.dto.FloodDto;
import com.safetynetapi.dto.FireDto;
import com.safetynetapi.dto.PersonInfoDto;

import java.util.List;
import java.util.Set;

public interface IAlertService {

    List<ChildAlertDto> childAlertService(String address);

    Set<String> phoneAlertService(String fireStationNumber);

    List<FireDto> fireService(String address);

    List<FloodDto> personOfStationService(String stations);

    List<PersonInfoDto> personWithMedicalRecordService(String firstName, String lastName);

    Set<String> EmailPerCityService(String city);
}
