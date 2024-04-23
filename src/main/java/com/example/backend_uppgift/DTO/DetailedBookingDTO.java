package com.example.backend_uppgift.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedBookingDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private CompressedCustomerDTO compCustomerDTO;
}
