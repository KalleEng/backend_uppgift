package com.example.backend_uppgift.controllers;


import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.Services.CustomerService;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerControllerTest {

        @Mock
        private CustomerService customerService;

        @Mock
        private BookingService bookingService;

        @Mock
        private RoomService roomService;

        @Mock
        private Model model;

        @InjectMocks
        private CustomerController customerController;

        private MockMvc mockMvc;

        @BeforeEach
        void setUp(){
                mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

                Customer mockCustomer = new Customer();
                mockCustomer.setId(1L);
                mockCustomer.setName("John Doe");
                mockCustomer.setEmail("doe@example.se");
                mockCustomer.setBookingList(new ArrayList<>());

                when(customerService.findById(any(Long.class))).thenReturn(mockCustomer);
        }

        /*@Test
        public void contextLoads() {
            assertThat(customerController).isNotNull();
        }*/

        @Test
        public void testDeleteCustomer() throws Exception {
                Long customerId = 1L;
                mockMvc.perform(get("/customers/delete/" + customerId))
                        .andExpect(status().isOk())
                        .andExpect(view().name("getCustomersFull"));
                verify(customerService).deleteById(customerId);
        }

        @Test
        public void testGetCustomerFull() throws Exception {
                mockMvc.perform(get("/customers/all"))
                        .andExpect(status().isOk())
                        .andExpect(view().name("getCustomersFull"));
                verify(customerService).getAllCustomers();
        }

        @Test
        public void testSaveEditedCustomer() throws Exception {
                Customer mockCustomer = new Customer("John Bro", "bro@example.se");
                mockMvc.perform(post("/customers/update")
                        .flashAttr("customer", mockCustomer))
                        .andExpect(status().isOk())
                        .andExpect(view().name("getCustomersFull"));
                verify(customerService).saveCustomer(mockCustomer);
        }

}