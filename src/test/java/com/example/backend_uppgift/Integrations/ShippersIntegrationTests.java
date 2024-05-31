package com.example.backend_uppgift.Integrations;

import com.example.backend_uppgift.FetchShippers;
import com.example.backend_uppgift.Services.ShipperService;
import com.example.backend_uppgift.Utils.StreamProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ShippersIntegrationTests {

    @Autowired
    ShipperService shipperService;

    @Autowired
    StreamProvider streamProvider;

    FetchShippers sut;

    @BeforeEach
    void setUp() {
        sut = new FetchShippers(shipperService, streamProvider);
    }

    @Test
    void getShippersWillFetch() throws IOException {
        String urlString = streamProvider.getDataStreamShippers();
        if (urlString == null || urlString.isEmpty()) {
            throw new IllegalArgumentException("URL is null or empty.");
        }


        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream();
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            String jsonContent = scanner.useDelimiter("\\A").next();

            System.out.println("Stream Content: " + jsonContent);

            assertAll("Shipper data fields",
                    () -> assertTrue(jsonContent.contains("\"id\""), "Field 'id' is missing"),
                    () -> assertTrue(jsonContent.contains("\"companyName\""), "Field 'companyName' is missing"),
                    () -> assertTrue(jsonContent.contains("\"phone\""), "Field 'phone' is missing")
            );
        } finally {
            connection.disconnect();
        }
    }
}
