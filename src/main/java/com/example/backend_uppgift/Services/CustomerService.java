package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.DTO.CompressedCustomerDTO;
import com.example.backend_uppgift.DTO.CompressedRoomDTO;
import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.models.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerService {
    public CompressedCustomerDTO customerToCompCustomerDTO(Customer customer);
    public DetailedCustomerDTO customerToDetailedCustomerDTO(Customer customer);

    public List<DetailedCustomerDTO> getAllCustomers();

    CompressedRoomDTO roomToCompRoomDTO(Room room);
    void saveCustomer(Customer customer);
    Customer findById(Long id);
    void deleteById(Long id);
    List<Customer> findAll();


}
