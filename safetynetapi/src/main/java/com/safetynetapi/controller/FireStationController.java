package com.safetynetapi.controller;

import com.safetynetapi.dto.FireStationDto;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.repository.ILoadingData;
import com.safetynetapi.service.IFireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class FireStationController {

      private IFireStationService fireStationService;
    @Autowired
    public FireStationController(IFireStationService fireStationService) {
         this.fireStationService = fireStationService;
    }

    @GetMapping("/firestation")
    public List<FireStationDto> listOfPersonOfStation(@RequestParam("stationNumber") String station_number){
        return fireStationService.personOfStationService(station_number);
    }

    @GetMapping("/firestationList")
    public List<FireStation> ListStation(){
        return fireStationService.fireStationList();
    }

    @PostMapping("/firestation")
    public ResponseEntity<FireStation> newFirestation(@RequestBody FireStation firestation){
        FireStation fireStationAdded = fireStationService.save(firestation);
        if(Objects.isNull(fireStationAdded)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(fireStationAdded)
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/firestation")
    public ResponseEntity<FireStation> updateFirestation(@RequestParam("address")String address,@RequestParam("station") String station){
        FireStation fireStationAdded = fireStationService.update(address,station);
        if(Objects.isNull(fireStationAdded)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(fireStationAdded)
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/firestation")
    public Map<String,Boolean> deleteFirestation(@RequestParam("address")String address, @RequestParam("station") String station){
        FireStation fireStationDelete = fireStationService.delete(address,station);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
