package com.example.backend_uppgift.Services;

import java.time.LocalDate;

public interface DiscountService {
    double calculateTotal(LocalDate startDate, LocalDate endDate, double price, Long customerId);
}
