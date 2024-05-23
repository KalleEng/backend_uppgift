package com.example.backend_uppgift.DTO;

import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueueViewModel {
    private UUID id;
    private String name;
    private String roomIdCSV;
    private int messagesToSend;

}
