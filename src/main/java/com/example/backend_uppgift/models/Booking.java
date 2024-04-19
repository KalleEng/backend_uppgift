package com.example.backend_uppgift.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToOne
    @JoinColumn
    private Room room;

    public Booking(LocalDate startDate, LocalDate endDate, Room room) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
    }
}
