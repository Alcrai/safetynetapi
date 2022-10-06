package com.safetynetapi.controller;

import com.safetynetapi.repository.ILoadingData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
@WebMvcTest(AlertController.class)
public class AlertControllerTest {
    @Autowired
    private MockMvc mvc;
   @MockBean
    private ILoadingData ILoadingData;



    @BeforeEach
    void initialiseTest(){

    }

    @Test
    void childAlertReturnAList(){

    }
}
