package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.DTO.CompressedCustomerDTO;
import com.example.backend_uppgift.DTO.DetailedCustomerDTO;
import com.example.backend_uppgift.models.Customer;

import java.util.List;

public interface CustomerService {
    void registerCustomer(String name, String email);
    void updateCustomer(Long customerId, String name, String email);
    boolean hasBookings(Long customerId);
    void deleteCustomer(Long customerId);
    public CompressedCustomerDTO customerToCompCustomerDTO(Customer customer);
    public DetailedCustomerDTO customerToDetailedCustomerDTO(Customer customer);

    public List<DetailedCustomerDTO> getAllCustomers();
}
