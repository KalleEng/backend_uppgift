package com.example.backend_uppgift;

import com.example.backend_uppgift.Services.ContractCustomerService;
import com.example.backend_uppgift.Utils.StreamProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ContractCustomersIntegrationTests {
    @Autowired
    ContractCustomerService contractCustomerService;
    @Autowired
    StreamProvider streamProvider;

    FetchContractCustomers sut;


    @Test
    void getContractCustomersWillFetch() throws IOException {
        sut = new FetchContractCustomers(contractCustomerService,streamProvider);
        Scanner s = new Scanner(sut.xmlStreamProvider.getDataStreamContractCustomers()).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        assertTrue( result.contains("<allcustomers>"));
        assertTrue( result.contains("</allcustomers>"));
        assertTrue( result.contains("<customers>"));
        assertTrue( result.contains("</customers>"));
        assertTrue( result.contains("<id>"));
        assertTrue( result.contains("</id>"));
        assertTrue( result.contains("<contactName>"));
        assertTrue( result.contains("</contactName>"));
        assertTrue( result.contains("<contactTitle>"));
        assertTrue( result.contains("</contactTitle>"));
        assertTrue( result.contains("<streetAddress>"));
        assertTrue( result.contains("</streetAddress>"));
        assertTrue( result.contains("<city>"));
        assertTrue( result.contains("</city>"));
        assertTrue( result.contains("</city>"));
        assertTrue( result.contains("<postalCode>"));
        assertTrue( result.contains("</postalCode>"));
        assertTrue( result.contains("<country>"));
        assertTrue( result.contains("</country>"));
        assertTrue( result.contains("<phone>"));
        assertTrue( result.contains("<fax>"));
        assertTrue( result.contains("</fax>"));
    }
}
