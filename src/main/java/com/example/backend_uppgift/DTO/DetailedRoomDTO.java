package com.example.backend_uppgift.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailedRoomDTO {
    private Long id;
    private boolean isAvailable;
    private boolean isDoubleRoom;
    private int numberOfBeds;
}
