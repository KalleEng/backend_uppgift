package com.example.backend_uppgift.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name is mandatory")
    private String name;
    @NotEmpty
    private String email;
    @OneToMany(mappedBy = "customer")
    private List<Booking> bookingList;



    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Customer(String name, String email, List<Booking> bookingList) {
        this.name = name;
        this.email = email;
        this.bookingList = bookingList;
    }
}
