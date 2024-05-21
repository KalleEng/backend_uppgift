package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.Services.Impl.DiscountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DiscountServiceImplTest {
   @Autowired
    DiscountServiceImpl discountService;

    @Test
   void discountIsAppliedCorrectly(){
       double sundayOneNight = discountService.calculateTotal(LocalDate.of(2024,05,19),
               LocalDate.of(2024,05,20),100);
       double twoNights = discountService.calculateTotal(LocalDate.of(2024,05,20),
               LocalDate.of(2024,05,22),100);
       double sundayTwoNights = discountService.calculateTotal(LocalDate.of(2024,05,19),
               LocalDate.of(2024,05,21),100);

       assertEquals(98,sundayOneNight);
       assertEquals(199,twoNights);
       assertEquals(197.01,sundayTwoNights);
   }
}
