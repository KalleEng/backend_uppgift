package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Long> {
}
