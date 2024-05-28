package com.example.backend_uppgift;

import com.example.backend_uppgift.Services.ContractCustomerService;
import com.example.backend_uppgift.Utils.AllCustomers;
import com.example.backend_uppgift.Utils.StreamProvider;
import com.example.backend_uppgift.models.ContractCustomer;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ComponentScan
public class FetchContractCustomers implements CommandLineRunner {
    ContractCustomerService customerService;
    public final StreamProvider streamProvider;

    public FetchContractCustomers(ContractCustomerService customerService, StreamProvider streamProvider) {
        this.customerService = customerService;
        this.streamProvider = streamProvider;
    }

    @Override
    public void run(String... args) throws Exception {

        List<ContractCustomer> customers = getContractCustomers();

        for (ContractCustomer c:
             customers) {
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

    public List<ContractCustomer> getContractCustomers() throws IOException {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper mapper = new XmlMapper(module);
        InputStream stream = streamProvider.getDataStreamContractCustomers();
        AllCustomers customers = mapper.readValue(stream, AllCustomers.class);
        return customers.customers;
    }
}