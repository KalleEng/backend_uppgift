package com.example.backend_uppgift.controllers;

import com.example.backend_uppgift.Services.BookingService;
import com.example.backend_uppgift.Services.RoomService;
import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.models.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookingControllerTest {

    @Mock
    private BookingService bookingService;
    @Mock
    private RoomService roomService;
    @Mock
    private Model model;
    @InjectMocks
    private BookingController bookingController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();

        Customer mockCustomer = mock(Customer.class);
        when(mockCustomer.getId()).thenReturn(1L);
        when(mockCustomer.getName()).thenReturn("John Doe");
        when(mockCustomer.getEmail()).thenReturn("doe@example.se");
        when(mockCustomer.getBookingList()).thenReturn(new ArrayList<>());

        Room mockRoom = mock(Room.class);
        when(mockRoom.getId()).thenReturn(1L);
        when(mockRoom.getBedCapacity()).thenReturn(2);
        when(mockRoom.getBookingList()).thenReturn(new ArrayList<>());

        Booking mockBooking = new Booking();
        mockBooking.setId(1L);
        mockBooking.setStartDate(LocalDate.now());
        mockBooking.setEndDate(LocalDate.now().plusDays(1));
        mockBooking.setCustomer(mockCustomer);
        mockBooking.setRoom(mockRoom);

        when(bookingService.findById(1L)).thenReturn(mockBooking);
    }

    @Test
    void testSearchWithValidData() throws Exception {
        mockMvc.perform(get("/bookings/search")
                        .param("startDate", "2024-01-01")
                        .param("endDate", "2024-01-02")
                        .param("numberOfPeople", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("roomSearch"));
    }

    @Test
    void testSearchWithInvalidData() throws Exception {
        mockMvc.perform(get("/bookings/search")
                        .param("startDate", "2024-01-01")
                        .param("endDate", "2023-12-31") // Invalid because end date is before start date
                        .param("numberOfPeople", "2"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("errors"));
    }
}
