package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.ContractCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContractCustomerRepo extends JpaRepository<ContractCustomer,Long> {

    @Query("Select c from ContractCustomer c where c.id = :contractCustomerId")
    Optional<ContractCustomer> getContractCustomerById(@Param("contractCustomerId") Long contractCustomerId);
}
