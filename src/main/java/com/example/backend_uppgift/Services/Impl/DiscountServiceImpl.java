package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.Services.DiscountService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

@Service
public class DiscountServiceImpl implements DiscountService {

    public double calculateTotal(LocalDate startDate, LocalDate endDate, double price){
        Period period = Period.between(startDate,endDate);
        int amountOfBookedDays = period.getDays();
        double total = 0.0;

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                total += price * (1 - 0.02);
            } else {
                total += price;
            }
        }

        if (amountOfBookedDays>=2){
            total = total * (1 - 0.005);
        }

        return total;
    }
}
