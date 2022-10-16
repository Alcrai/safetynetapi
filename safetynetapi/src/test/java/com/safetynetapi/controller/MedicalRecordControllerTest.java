package com.safetynetapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getMedicalRecord_success() throws Exception {
        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Boyd")))
                .andExpect(jsonPath("$[0].birthdate", is("03/06/1984")))
                .andExpect(jsonPath("$[0].medications", is("[\"aznol:350mg\", \"hydrapermazol:100mg\"]")))
                .andExpect(jsonPath("$[0].allergies", is("[\"nillacilan\"]")));
    }

    @Test
    public void postMedicalRecord_success() throws Exception {
        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "        \"firstName\": \"Alex\",\n" +
                                "        \"lastName\": \"Bland\",\n" +
                                "        \"birthdate\": \"03/06/1984\",\n" +
                                "        \"medications\": \"[\\\"aznol:350mg\\\", \\\"hydrapermazol:100mg\\\"]\",\n" +
                                "        \"allergies\": \"[\\\"nillacilan\\\"]\",\n" +
                                "        \"age\": 38\n" +
                                "    }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void putMedicalRecord_success() throws Exception {
        mockMvc.perform(put("/medicalRecord")
                        .param("firstName","Tenley")
                        .param("lastName","Boyd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "        \"firstName\": \"Alex\",\n" +
                                "        \"lastName\": \"Bland\",\n" +
                                "        \"birthdate\": \"23/10/1978\",\n" +
                                "        \"medications\": \"[\\\"aznol:350mg\\\", \\\"hydrapermazol:100mg\\\"]\",\n" +
                                "        \"allergies\": \"[\\\"nillacilan\\\"]\",\n" +
                                "        \"age\": 38\n" +
                                "    }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteMedicalRecord_success() throws Exception {
        mockMvc.perform(delete("/medicalRecord")
                        .param("firstName","Jacob")
                        .param("lastName","Boyd"))
                .andExpect(status().isOk());
    }
}
