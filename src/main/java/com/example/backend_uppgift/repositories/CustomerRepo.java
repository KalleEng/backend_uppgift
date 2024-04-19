package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
