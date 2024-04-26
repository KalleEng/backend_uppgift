package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
