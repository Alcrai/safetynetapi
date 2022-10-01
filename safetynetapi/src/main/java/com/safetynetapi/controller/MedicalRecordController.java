package com.safetynetapi.controller;

import com.safetynetapi.model.MedicalRecord;
import com.safetynetapi.repository.LoadingData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MedicalRecordController {

    private LoadingData loadingData;

    public MedicalRecordController(LoadingData loadingData) {
        this.loadingData = loadingData;
    }

    @GetMapping("medicalRecord")
    public List<String> listMedicalRecord(){
        List<String> listemedical= new ArrayList<>();
        loadingData.findAllMedicalRecord().forEach(md ->listemedical.add(md.toString()));
        return listemedical;
    }
}
