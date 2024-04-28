package com.example.backend_uppgift.DTO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedCustomerDTO {
    private Long id;
    private String name;
    private String email;
    private List<CompressedBookingDTO> compBookingDTO;


}
