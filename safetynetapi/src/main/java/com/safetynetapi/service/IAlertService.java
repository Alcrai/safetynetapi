package com.safetynetapi.service;

import com.safetynetapi.model.dto.ChildAlertDTO;
import com.safetynetapi.model.dto.FloodDTO;
import com.safetynetapi.model.dto.FireDTO;
import com.safetynetapi.model.dto.PersonInfoDTO;

import java.util.List;
import java.util.Set;

public interface IAlertService {

    List<ChildAlertDTO> childAlertService(String address);

    Set<String> phoneAlertService(String fireStationNumber);

    List<FireDTO> fireService(String address);

    List<FloodDTO> personOfStationService(String stations);

    List<PersonInfoDTO> personWithMedicalRecordService(String firstName, String lastName);

    Set<String> emailPerCityService(String city);
}
