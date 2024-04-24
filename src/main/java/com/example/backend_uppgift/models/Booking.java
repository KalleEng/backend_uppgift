package com.example.backend_uppgift.models;

import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToOne
    @JoinColumn
    private Room room;

    public Booking(LocalDate startDate, LocalDate endDate, Room room, Customer customer) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.customer = customer;
    }
}

