package com.safetynetapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetapi.dto.ChildAlertDto;
import com.safetynetapi.service.IAlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AlertController.class)
@WebMvcTest(AlertController.class)
public class AlertControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private IAlertService alertService;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void initialiseTest(){

    }

    @Test
    void childAlertReturnAList() throws Exception {
     /*   List<String> family = new ArrayList<>();
        family.add("maman");
        given(alertService.childAlertService("1509 Culver St")).willReturn(asList(
                new ChildAlertDto("Alex","Blandio",2,family)
        ));
        this.mvc.perform(get("/childAlert?address=1509 Culver St"))
                .andExpect(status().isOk());*/
    }
}
