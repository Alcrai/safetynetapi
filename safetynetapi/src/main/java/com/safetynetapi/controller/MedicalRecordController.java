package com.safetynetapi.controller;

import com.safetynetapi.repository.ILoadingData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MedicalRecordController {

    private ILoadingData ILoadingData;

    public MedicalRecordController(ILoadingData ILoadingData) {
        this.ILoadingData = ILoadingData;
    }

    @GetMapping("medicalRecord")
    public List<String> listMedicalRecord(){
        List<String> listemedical= new ArrayList<>();
        ILoadingData.findAllMedicalRecord().forEach(md ->listemedical.add(md.toString()));
        return listemedical;
    }
}
