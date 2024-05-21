package com.example.backend_uppgift.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedRoomDTO {
    private Long id;
    private int bedCapacity;
    private double price;
    private List<CompressedBookingDTO> bookingList;
}
