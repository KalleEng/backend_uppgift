package com.example.backend_uppgift.Integrations;

import com.example.backend_uppgift.FetchContractCustomers;
import com.example.backend_uppgift.Services.ContractCustomerService;
import com.example.backend_uppgift.Utils.RestHelper;
import com.example.backend_uppgift.Utils.StreamProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ContractCustomersIntegrationTests {

    @Autowired
    ContractCustomerService contractCustomerService;

    @Autowired
    StreamProvider streamProvider;

    @Mock
    RestHelper restHelper;

    FetchContractCustomers sut;

    @BeforeEach
    void setUp() {
        sut = new FetchContractCustomers(contractCustomerService, streamProvider, restHelper);
    }

    @Test
    void getContractCustomersWillFetch() throws IOException {
        String urlString = streamProvider.getDataStreamContractCustomers();
        if (urlString == null || urlString.isEmpty()) {
            throw new IllegalArgumentException("URL is null or empty.");
        }

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream();
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            String xmlContent = scanner.useDelimiter("\\A").next();

            System.out.println("Stream Content: " + xmlContent);

            assertTrue(xmlContent.contains("<allcustomers>"));
            assertTrue(xmlContent.contains("</allcustomers>"));
            assertTrue(xmlContent.contains("<customers>"));
            assertTrue(xmlContent.contains("</customers>"));
            assertTrue(xmlContent.contains("<id>"));
            assertTrue(xmlContent.contains("</id>"));
            assertTrue(xmlContent.contains("<contactName>"));
            assertTrue(xmlContent.contains("</contactName>"));
            assertTrue(xmlContent.contains("<contactTitle>"));
            assertTrue(xmlContent.contains("</contactTitle>"));
            assertTrue(xmlContent.contains("<streetAddress>"));
            assertTrue(xmlContent.contains("</streetAddress>"));
            assertTrue(xmlContent.contains("<city>"));
            assertTrue(xmlContent.contains("</city>"));
            assertTrue(xmlContent.contains("<postalCode>"));
            assertTrue(xmlContent.contains("</postalCode>"));
            assertTrue(xmlContent.contains("<country>"));
            assertTrue(xmlContent.contains("</country>"));
            assertTrue(xmlContent.contains("<phone>"));
            assertTrue(xmlContent.contains("</phone>"));
            assertTrue(xmlContent.contains("<fax>"));
            assertTrue(xmlContent.contains("</fax>"));
        } finally {
            connection.disconnect();
        }
    }
}
