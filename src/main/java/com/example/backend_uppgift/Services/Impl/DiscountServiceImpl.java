package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.Services.DiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Service
public class DiscountServiceImpl implements DiscountService {

    public double calculateTotal(LocalDate startDate, LocalDate endDate, double price){
        double total = 0.0;

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                total += price * (1 - 0.02);
            } else {
                total += price;
            }
        }
        long amountOfBookedDays = startDate.until(endDate, ChronoUnit.DAYS);

        if (amountOfBookedDays>=2){
            total = total * (1 - 0.005);
        }

        BigDecimal bd = BigDecimal.valueOf(total);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
