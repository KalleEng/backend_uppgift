package com.example.backend_uppgift.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerControllerTest {

        @Autowired
        private CustomerController customerController;

        @Test
        public void contextLoads() {
            assertThat(customerController).isNotNull();
        }


}