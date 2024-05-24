package com.example.backend_uppgift.models;

import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id @GeneratedValue
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne @JoinColumn @NotNull
    private Customer customer;
    @ManyToOne @JoinColumn @NotNull
    private Room room;
    private double total;

    public Booking(LocalDate startDate, LocalDate endDate, Room room, Customer customer, double total) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.customer = customer;
        this.total = total;
    }

}

