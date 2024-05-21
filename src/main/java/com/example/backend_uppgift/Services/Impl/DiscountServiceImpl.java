package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.Services.DiscountService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class DiscountServiceImpl implements DiscountService {

    public double calculateTotal(LocalDate startDate, LocalDate endDate, double price){
        Period period = Period.between(startDate,endDate);
        int amountOfBookedDays = period.getDays();
        double total = amountOfBookedDays*price;
        if (amountOfBookedDays>=2){
            total = total*(1-(0.5/100));
        }

        return total;
    }
}
