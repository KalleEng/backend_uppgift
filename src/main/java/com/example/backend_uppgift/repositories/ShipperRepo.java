package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipperRepo extends JpaRepository<Shipper,Long > {
}
