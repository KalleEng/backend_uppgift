package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.Services.Impl.DiscountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DiscountServiceImplTest {
   @Autowired
    DiscountServiceImpl discountService;

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
