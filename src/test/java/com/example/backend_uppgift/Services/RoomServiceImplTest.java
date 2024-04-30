package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.Services.Impl.RoomServiceImpl;
import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.repositories.BookingRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RoomServiceImplTest {

    @Mock
    private BookingRepo bookingRepo;

    @InjectMocks
    private RoomServiceImpl roomService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRoomAvailable_NoBooking_ReturnTrue(){
        Long roomId = 1L;
        LocalDate startDate = LocalDate.of(2022,10,10);
        LocalDate endDate = LocalDate.of(2022,10,12);
        int numberOfPeople = 1;

        when(bookingRepo.findByRoomId(roomId)).thenReturn(List.of());

        assertTrue(roomService.isAvailable(roomId,startDate,endDate,numberOfPeople));
    }

    @Test
    void testRoomNotAvailable_OverlappingExistingBooking_ReturnFalse(){
        Long roomId = 1L;
        LocalDate startDate = LocalDate.of(2024,4,20);
        LocalDate endDate = LocalDate.of(2024,4,21);
        Booking existingBooking = new Booking(null, startDate, endDate,null, null);
        int numberOfPeople = 1;
        when(bookingRepo.findByRoomId(roomId)).thenReturn(List.of(existingBooking));

        assertFalse(roomService.isAvailable(roomId,startDate,endDate, numberOfPeople));
    }

    @Test
    void testRoomAvailable_BookingAfterExisting_ReturnTrue(){
        Long roomId = 1L;
        LocalDate startDate = LocalDate.of(2024,4,20);
        LocalDate endDate = LocalDate.of(2024,4,21);
        LocalDate newStartDate = LocalDate.of(2024, 4,25);
        LocalDate newEndDate = LocalDate.of(2024,4,27);
        Booking existingBooking = new Booking(null, startDate, endDate,null, null);
        int numberOfPeople = 1;

        when(bookingRepo.findByRoomId(roomId)).thenReturn(List.of(existingBooking));

        assertTrue(roomService.isAvailable(roomId, newStartDate, newEndDate,numberOfPeople));
    }
}
