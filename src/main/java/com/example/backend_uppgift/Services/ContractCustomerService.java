package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.models.ContractCustomer;
import com.example.backend_uppgift.models.Customer;

import java.util.List;

public interface ContractCustomerService {
    void saveCustomer(ContractCustomer contractCustomer);

    List<ContractCustomer> getAllCustomers();
}
