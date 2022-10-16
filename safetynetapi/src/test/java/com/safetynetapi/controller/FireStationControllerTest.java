package com.safetynetapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getFireStation_success() throws Exception {
        mockMvc.perform(get("/firestation").param("stationNumber","3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(14)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Boyd")))
                .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
                .andExpect(jsonPath("$[0].city", is("Culver")))
                .andExpect(jsonPath("$[0].phone", is("841-874-6512")))
                .andExpect(jsonPath("$[0].numberOfAdult", is(0)))
                .andExpect(jsonPath("$[0].numberOfChildren", is(0)));
    }

    @Test
    public void getFireStationList_success() throws Exception {
        mockMvc.perform(get("/firestationList"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(13)))
                .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
                .andExpect(jsonPath("$[0].station", is("3")));
    }

    @Test
    public void postFireStation_success() throws Exception {
        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"address\":\"1 Culver St\", \"station\":\"5\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void putFireStation_success() throws Exception {
        mockMvc.perform(put("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("address","1509 Culver St")
                        .param("station","6"))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteFireStation_success() throws Exception {
        mockMvc.perform(delete("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("address","1509 Culver St")
                        .param("station","6"))
                .andExpect(status().isOk());
    }

}
