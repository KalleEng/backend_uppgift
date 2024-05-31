package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.Services.Impl.CustomerServiceImpl;
import com.example.backend_uppgift.Services.Impl.DiscountServiceImpl;
import com.example.backend_uppgift.models.Booking;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.repositories.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


//@SpringBootTest
public class DiscountServiceImplTest {
    @Mock
    private CustomerRepo customerRepo;
    @Mock
    private CustomerServiceImpl customerService = Mockito.mock(CustomerServiceImpl.class);

    DiscountServiceImpl discountService = new DiscountServiceImpl(customerService);

    private Customer customer;

    @BeforeEach
    void setUp() {
        Customer customer = new Customer("Alex", "a.b@hotmail.com");
        List<Booking> bookingList = new ArrayList<>();
        customer.setBookingList(bookingList);

        when(customerService.findById(1L)).thenReturn(customer);
    }

    @Test
   void discountIsAppliedCorrectly(){
       double sundayOneNight = discountService.calculateTotal(LocalDate.of(2024,05,19),
               LocalDate.of(2024,05,20),100,1L);
       double twoNights = discountService.calculateTotal(LocalDate.of(2024,05,20),
               LocalDate.of(2024,05,22),100,1L);
       double sundayTwoNights = discountService.calculateTotal(LocalDate.of(2024,05,19),
               LocalDate.of(2024,05,21),100,1L);

       assertEquals(98,sundayOneNight);
       assertEquals(199,twoNights);
       assertEquals(197.01,sundayTwoNights);

   }

   @Test
   void tenPlusNightsIsTrue(){
        boolean isTenPlusDays = discountService.discountForMoreThanTenNights(LocalDate.of(2024,5,1),
                LocalDate.of(2024,5,10),1L);
        assertTrue(isTenPlusDays);
   }
}
