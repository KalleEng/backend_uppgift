package com.example.backend_uppgift.repositories;

import com.example.backend_uppgift.models.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    //void changeCustomerName(String newVal, String oldVal);
    @Modifying
    @Transactional
    @Query(value = "UPDATE customer set name = :newVal WHERE name = :oldVal",nativeQuery = true)
    void changeCustomerName(String newVal,String oldVal);

    @Modifying
    @Transactional
    @Query(value = "UPDATE customer set email = :newVal WHERE email = :oldVal",nativeQuery = true)
    void changeCustomerEmail(String newVal, String oldVal);
}
