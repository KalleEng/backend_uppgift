package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.Services.Impl.BookingServiceImpl;
import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.repositories.BookingRepo;
import com.example.backend_uppgift.repositories.CustomerRepo;
import com.example.backend_uppgift.repositories.RoomRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {

    @Mock
    private BookingRepo bookingRepo;
    @Mock
    private CustomerRepo customerRepo;
    @Mock
    private RoomRepo roomRepo;
    @Mock
    private RoomService roomService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking_RoomNotAvailable_ThrowsException(){
        LocalDate startDate = LocalDate.of(2024,4,20);
        LocalDate endDate = LocalDate.of(2024,4,21);
        Long roomId = 1L;
        Long customerId = 1L;

        when(roomService.isAvailable(roomId,startDate,endDate)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            bookingService.createBooking(startDate,endDate,roomId,customerId);
        });

        verify(bookingRepo, never()).save(any(Booking.class));
    }

}
