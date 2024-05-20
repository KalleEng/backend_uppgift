package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShipperRepo extends JpaRepository<Shipper,Long > {

    @Query("SELECT s FROM Shipper s")
    List<Shipper> getAllShippersFromDataBase();
}
