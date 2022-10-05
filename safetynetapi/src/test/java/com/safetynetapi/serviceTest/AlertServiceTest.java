package com.safetynetapi.serviceTest;

import com.safetynetapi.repository.ILoadingData;
import com.safetynetapi.service.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(classes = AlertService.class)
public class AlertServiceTest {
/*
   @Autowired
    private ILoadingData iLoadingData;

    private AlertService alertService;

    @BeforeEach
    public void initTest(){
        alertService = new AlertService(iLoadingData);
    }

    @Test
    public void childAlertServiceIsNotNull(){
       assertThat(alertService.childAlertService("1509 Culver St")).isNotEmpty();
    }*/
}
