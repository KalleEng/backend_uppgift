package com.example.backend_uppgift.DTO;

import com.example.backend_uppgift.models.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompressedBookingDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private CompressedRoomDTO compRoom;
}
