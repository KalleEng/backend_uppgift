package com.example.backend_uppgift.Integrations;

import com.example.backend_uppgift.Configurations.IntegrationProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class IntegrationPropertiesTests {

    @Autowired
    private IntegrationProperties integrationProperties;

    @Test
    public void testThatUrlActuallyGetUrl(){
        assertThat(integrationProperties.getContractCustomerProperties().getUrl()).isEqualTo("https://javaintegration.systementor.se/customers");
        assertThat(integrationProperties.getShipperProperties().getUrl()).isEqualTo("https://javaintegration.systementor.se/shippers");
        assertThat(integrationProperties.getBlacklistProperties().getUrl()).isEqualTo("https://javabl.systementor.se/api/stefan/blacklistcheck/");
    }
}
