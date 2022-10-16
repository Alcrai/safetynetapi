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
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPerson_success() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Boyd")))
                .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
                .andExpect(jsonPath("$[0].city", is("Culver")))
                .andExpect(jsonPath("$[0].zip", is("97451")))
                .andExpect(jsonPath("$[0].phone", is("841-874-6512")))
                .andExpect(jsonPath("$[0].email", is("jaboyd@email.com")));
    }

    @Test
    public void postPerson_success() throws Exception {
        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\":\"Alex\", \"lastName\":\"Bland\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"alexbland@email.com\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void putPerson_success() throws Exception {
        mockMvc.perform(put("/person")
                        .param("firstName","Alex")
                        .param("lastName","Bland")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\":\"Alex\", \"lastName\":\"Bland\", \"address\":\"1 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"alexbland@email.com\" }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void deletePerson_success() throws Exception {
        mockMvc.perform(delete("/person")
                        .param("firstName","Jacob")
                        .param("lastName","Boyd"))
                .andExpect(status().isOk());
    }
}
