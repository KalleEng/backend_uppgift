package com.example.backend_uppgift.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue
    private Long id;
    private int bedCapacity;
    private double price;
    @OneToMany
    private List<Booking> bookingList;

    public Room(int bedCapacity, double price) {
        this.bedCapacity = bedCapacity;
        this.price = price;
    }

    public Room(int bedCapacity, double price) {
        this.isAvailable = true;
        this.bedCapacity = bedCapacity;
        this.price = price;
    }
}
