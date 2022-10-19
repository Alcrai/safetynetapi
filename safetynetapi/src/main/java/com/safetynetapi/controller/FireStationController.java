package com.safetynetapi.controller;

import com.safetynetapi.dto.FireStationDTO;
import com.safetynetapi.model.FireStation;
import com.safetynetapi.service.IFireStationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@Log4j2
@RestController
public class FireStationController {

    private IFireStationService fireStationService;
    @Autowired
    public FireStationController(IFireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping("/firestation")
    public List<FireStationDTO> getFireStation(@RequestParam("stationNumber") String station_number){
        log.info("Request:GET /firestation?stationNumber="+ station_number);
        log.info("Response:");
        fireStationService.personOfStationService(station_number).forEach(as->log.info(as.toString()));
        return fireStationService.personOfStationService(station_number);
    }

    @GetMapping("/firestationList")
    public List<FireStation> getFireStationList(){
        log.info("Request:GET /firestation");
        log.info("Response:");
        fireStationService.fireStationList().forEach(as->log.info(as.toString()));
        return fireStationService.fireStationList();
    }

    @PostMapping("/firestation")
    public ResponseEntity<FireStation> postFireStation(@RequestBody FireStation firestation){
        FireStation fireStationAdded = fireStationService.save(firestation);
        if(Objects.isNull(fireStationAdded)){
            return ResponseEntity.noContent().build();
        }
        log.info("Request:POST /firestation" + firestation);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(fireStationAdded)
                .toUri();
        log.info("Response:"+ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/firestation")
    public ResponseEntity<FireStation> putFireStation(@RequestParam("address")String address,@RequestParam("station") String station){
        FireStation fireStationAdded = fireStationService.update(address,station);
        log.info("Request:PUT /firestation?address="+address+"&station="+station);
        if(Objects.isNull(fireStationAdded)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(fireStationAdded)
                .toUri();
        log.info("Response:"+ResponseEntity.created(location).build());
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/firestation")
    public Map<String,Boolean> deleteFireStation(@RequestParam("address") String address, @RequestParam("station") String station){
        FireStation fireStationDelete = fireStationService.delete(address,station);
        log.info("Request:DELETE /firestation?address="+address+"&station="+station);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        log.info("Response:"+ response);
        return response;
    }


}
