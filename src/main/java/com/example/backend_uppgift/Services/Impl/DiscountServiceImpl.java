package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.Services.CustomerService;
import com.example.backend_uppgift.Services.DiscountService;
import com.example.backend_uppgift.models.Booking;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    CustomerService customerService;

    public DiscountServiceImpl(CustomerService customerService){
        this.customerService = customerService;
    }

    public double calculateTotal(LocalDate startDate, LocalDate endDate, double price, Long customerId){
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

        if(discountForMoreThanTenNights(startDate,endDate,customerId)){
            total = total*(1-0.02);
            System.out.println("price discounted for 10+ nights within the previous year");
        }

        BigDecimal bd = BigDecimal.valueOf(total);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public boolean discountForMoreThanTenNights(LocalDate startDate, LocalDate endDate, Long customerId) {
        List<Booking> bookingList = customerService.findById(customerId).getBookingList();

        LocalDate startOfPreviousYear = startDate.minusYears(1);
        LocalDate endOfPreviousYear = startDate.minusYears(1).plusDays(364);

        long totalNights = bookingList.stream()
                .filter(booking -> !booking.getEndDate().isBefore(startOfPreviousYear) && !booking.getStartDate().isAfter(endOfPreviousYear))
                .mapToLong(booking -> {
                    LocalDate startDateBooking = booking.getStartDate().isBefore(startOfPreviousYear) ? startOfPreviousYear : booking.getStartDate();
                    LocalDate endDateBooking = booking.getEndDate().isAfter(endOfPreviousYear) ? endOfPreviousYear : booking.getEndDate();
                    return ChronoUnit.DAYS.between(startDateBooking, endDateBooking) + 1;
                })
                .sum();

        return totalNights > 10;
    }

}
