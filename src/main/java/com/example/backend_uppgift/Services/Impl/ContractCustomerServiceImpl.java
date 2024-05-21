package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.Services.ContractCustomerService;
import com.example.backend_uppgift.models.ContractCustomer;
import com.example.backend_uppgift.models.Customer;
import com.example.backend_uppgift.repositories.ContractCustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractCustomerServiceImpl implements ContractCustomerService {
    private final ContractCustomerRepo customerRepo;

    public ContractCustomerServiceImpl(ContractCustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }
    @Override
    public void saveCustomer(ContractCustomer contractCustomer){customerRepo.save(contractCustomer);}

    @Override
    public List<ContractCustomer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public ContractCustomer findById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }
}
