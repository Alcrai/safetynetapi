package com.safetynetapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getChildAlert_success() throws Exception {
        mockMvc.perform(get("/childAlert").param("address","1509 Culver St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("Tenley")))
                .andExpect(jsonPath("$[0].lastName", is("Boyd")))
                .andExpect(jsonPath("$[0].family", hasSize(5)));
    }

    @Test
    public void getPhoneAlert_success() throws Exception {
        mockMvc.perform(get("/phoneAlert").param("firestation", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(7)))
                .andExpect(jsonPath("$[0]", is("841-874-6741")));
    }

    @Test
    public void getFire_success() throws Exception {
        mockMvc.perform(get("/fire").param("address","1509 Culver St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Boyd")))
                .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
                .andExpect(jsonPath("$[0].city", is("Culver")))
                .andExpect(jsonPath("$[0].station", is("3")))
                .andExpect(jsonPath("$[0].phone", is("841-874-6512")))
                .andExpect(jsonPath("$[0].medications", is("[\"aznol:350mg\", \"hydrapermazol:100mg\"]")))
                .andExpect(jsonPath("$[0].allergies", is("[\"nillacilan\"]")));
    }

    @Test
    public void getFloodStations_success() throws Exception {
        mockMvc.perform(get("/flood/stations").param("stations","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].address", is("644 Gershwin Cir")))
                .andExpect(jsonPath("$[0].station", is("1")))
                .andExpect(jsonPath("$[0].firstName", is("Peter")))
                .andExpect(jsonPath("$[0].lastName", is("Duncan")))
                .andExpect(jsonPath("$[0].phone", is("841-874-6512")))
                .andExpect(jsonPath("$[0].medications", is("[]")))
                .andExpect(jsonPath("$[0].allergies", is("[\"shellfish\"]")));
    }

    @Test
    public void getPersonInfo_success() throws Exception {
        mockMvc.perform(get("/personInfo").param("firstName","Reginold")
                        .param("lastName","Walker"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is("Reginold")))
                .andExpect(jsonPath("$[0].lastName", is("Walker")))
                .andExpect(jsonPath("$[0].address", is("908 73rd St")))
                .andExpect(jsonPath("$[0].city", is("Culver")))
                .andExpect(jsonPath("$[0].mail", is("reg@email.com")))
                .andExpect(jsonPath("$[0].medications", is("[\"thradox:700mg\"]")))
                .andExpect(jsonPath("$[0].allergies", is("[\"illisoxian\"]")));
    }

    @Test
    public void getCommunityEmail_success() throws Exception {
        mockMvc.perform(get("/communityEmail").param("city","Culver"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(15)))
                .andExpect(jsonPath("$[0]", is("drk@email.com")));
    }
}
