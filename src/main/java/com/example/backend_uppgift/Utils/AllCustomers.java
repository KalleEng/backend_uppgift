package com.example.backend_uppgift.Utils;

import com.example.backend_uppgift.models.ContractCustomer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "allcustomers")
public class AllCustomers {
    public List<ContractCustomer> customers;
}
