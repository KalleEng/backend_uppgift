package com.example.backend_uppgift.Services;

import com.example.backend_uppgift.models.ContractCustomer;
import com.example.backend_uppgift.models.Customer;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ContractCustomerService {
    void saveCustomer(ContractCustomer contractCustomer);

    List<ContractCustomer> getAllCustomers();

    ContractCustomer findById(Long id);

    List<ContractCustomer> findAll(Sort sort);

    List<ContractCustomer> findAllByCompanyNameContains(String companyName, Sort sort);
}
