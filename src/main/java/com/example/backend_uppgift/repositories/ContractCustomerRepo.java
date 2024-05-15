package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.ContractCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractCustomerRepo extends JpaRepository<ContractCustomer,Long> {
}
