package com.example.backend_uppgift;

import com.example.backend_uppgift.Services.ContractCustomerService;
import com.example.backend_uppgift.Utils.AllCustomers;
import com.example.backend_uppgift.models.ContractCustomer;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.URL;

@ComponentScan
public class FetchContractCustomers implements CommandLineRunner {
    ContractCustomerService customerService;

    public FetchContractCustomers(ContractCustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) throws Exception {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper mapper = new XmlMapper(module);

        AllCustomers customers = mapper.readValue(new URL("https://javaintegration.systementor.se/customers"),
                AllCustomers.class);

        for (ContractCustomer c:
             customers.customers) {
            customerService.saveCustomer(new ContractCustomer(c.id,
                    c.companyName,
                    c.contactName,
                    c.contactTitle,
                    c.streetAddress,
                    c.city,
                    c.postalCode,
                    c.country,
                    c.phone,
                    c.fax));
        }
    }
}
