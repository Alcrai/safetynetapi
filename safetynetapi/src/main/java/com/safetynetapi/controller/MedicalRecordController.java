package com.safetynetapi.controller;

import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.service.IMedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
public class MedicalRecordController {

    private IMedicalRecordService medicalRecordService;

    public MedicalRecordController(IMedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/medicalRecord")
    public List<MedicalRecord> getMedicalRecord(){
        return medicalRecordService.findAllMedicalRecord();
    }

    @PostMapping("/medicalRecord")
    public ResponseEntity<MedicalRecord> postMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        MedicalRecord medicalRecordAdded = medicalRecordService.saveMedicalRecord(medicalRecord);
        if(Objects.isNull(medicalRecordAdded)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(medicalRecordAdded)
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/medicalRecord")
    public ResponseEntity<MedicalRecord> putMedicalRecord(@RequestParam("firstName")String firstName,@RequestParam("lastName") String lastName,@RequestBody MedicalRecord medicalRecord){
        MedicalRecord medicalRecordAdded = medicalRecordService.updateMedicalRecord(firstName,lastName,medicalRecord);
        if(Objects.isNull(medicalRecordAdded)){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(medicalRecordAdded)
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/medicalRecord")
    public Map<String,Boolean> deleteMedicalRecord(@RequestParam("firstName") String firstName, @RequestParam("lastName")String lastName){
        MedicalRecord medicalRecordDelete = medicalRecordService.deleteMedicalRecord(firstName,lastName);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
