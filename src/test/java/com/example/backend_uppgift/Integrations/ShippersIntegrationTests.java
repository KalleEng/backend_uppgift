package com.example.backend_uppgift.Integrations;

import com.example.backend_uppgift.FetchShippers;
import com.example.backend_uppgift.Services.ShipperService;
import com.example.backend_uppgift.Utils.StreamProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ShippersIntegrationTests {
    @Autowired
    ShipperService shipperService;
    @Autowired
    StreamProvider streamProvider;

    FetchShippers sut;

    @Test
    void getShippersWillFetch()throws IOException {
        sut = new FetchShippers(shipperService, streamProvider);
        String result;
        try(Scanner s = new Scanner(sut.streamProvider.getDataStreamShippers()).useDelimiter("\\A")) {
            result = s.hasNext() ? s.next() : "";
        }

        assertAll("Shipper data fields",
                () -> assertTrue(result.contains("id"), "Field 'id' is missing"),
                () -> assertTrue(result.contains("companyName"), "Field 'companyName' is missing"),
                () -> assertTrue(result.contains("phone"), "Field 'phone' is missing"));

        /*assertAll("Shipper data fields",
                () -> assertTrue(result.contains("id"), "Field 'id' is missing"),
                () -> assertTrue(result.contains("email"), "Field 'email' is missing"),
                () -> assertTrue(result.contains("companyName"), "Field 'companyName' is missing"),
                () -> assertTrue(result.contains("contactName"), "Field 'contactName' is missing"),
                () -> assertTrue(result.contains("contactTitle"), "Field 'contactTitle' is missing"),
                () -> assertTrue(result.contains("streeAddress"), "Field 'streetAddress' is missing"),
                () -> assertTrue(result.contains("city"), "Field 'city' is missing"),
                () -> assertTrue(result.contains("postalCode"), "Field 'postalCode' is missing"),
                () -> assertTrue(result.contains("country"), "Field 'country' is missing"),
                () -> assertTrue(result.contains("phone"), "Field 'phone' is missing"),
                () -> assertTrue(result.contains("fax"), "Field 'fax' is missing")
        );*/
        /*assertTrue(result.contains("id"));
        assertTrue(result.contains("email"));
        assertTrue(result.contains("companyName"));
        assertTrue(result.contains("contactName"));
        assertTrue(result.contains("contactTitle"));
        assertTrue(result.contains("streetAddress"));
        assertTrue(result.contains("city"));
        assertTrue(result.contains("postalCode"));
        assertTrue(result.contains("country"));
        assertTrue(result.contains("phone"));
        assertTrue(result.contains("fax"));*/
    }
}
