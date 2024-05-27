package com.example.backend_uppgift.Services.Impl;

import com.example.backend_uppgift.Services.ContractCustomerService;
import com.example.backend_uppgift.models.ContractCustomer;
import com.example.backend_uppgift.repositories.ContractCustomerRepo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.List;
import java.util.Locale;

@Service
public class ContractCustomerServiceImpl implements ContractCustomerService {
    private final ContractCustomerRepo customerRepo;

    public ContractCustomerServiceImpl(ContractCustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public void saveCustomer(ContractCustomer contractCustomer) {
        customerRepo.save(contractCustomer);
    }

    @Override
    public List<ContractCustomer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public ContractCustomer findById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }

    /*
    @Override
    public List<ContractCustomer> findAll(Sort sort) {
        return customerRepo.findAll(sort);
    }
     */

    @Override
    public List<ContractCustomer> findAll(Sort sort) {
        List<ContractCustomer> customers = customerRepo.findAll(sort);
        Collator collator = Collator.getInstance(new Locale("sv", "SE"));

        for (Sort.Order order : sort) {
            switch (order.getProperty().toLowerCase()) {
                case "companyname":
                    customers.sort((c1, c2) -> {
                        int result = collator.compare(c1.getCompanyName(), c2.getCompanyName());
                        return order.isAscending() ? result : -result;
                    });
                    break;
                case "contactname":
                    customers.sort((c1, c2) -> {
                        int result = collator.compare(c1.getContactName(), c2.getContactName());
                        return order.isAscending() ? result : -result;
                    });
                    break;
                case "country":
                    customers.sort((c1, c2) -> {
                        int result = collator.compare(c1.getCountry(), c2.getCountry());
                        return order.isAscending() ? result : -result;
                    });
                    break;
            }
        }

        return customers;
    }

    @Override
    public List<ContractCustomer> findAllByCompanyNameContains(String companyName, Sort sort) {
        List<ContractCustomer> customers = customerRepo.findAllByCompanyNameContains(companyName, sort);
        Collator collator = Collator.getInstance(new Locale("sv", "SE"));

        for (Sort.Order order : sort) {
            switch (order.getProperty().toLowerCase()) {
                case "companyname":
                    customers.sort((c1, c2) -> {
                        int result = collator.compare(c1.getCompanyName(), c2.getCompanyName());
                        return order.isAscending() ? result : -result;
                    });
                    break;
                case "contactname":
                    customers.sort((c1, c2) -> {
                        int result = collator.compare(c1.getContactName(), c2.getContactName());
                        return order.isAscending() ? result : -result;
                    });
                    break;
                case "country":
                    customers.sort((c1, c2) -> {
                        int result = collator.compare(c1.getCountry(), c2.getCountry());
                        return order.isAscending() ? result : -result;
                    });
                    break;
            }
        }

        return customers;
    }
}
