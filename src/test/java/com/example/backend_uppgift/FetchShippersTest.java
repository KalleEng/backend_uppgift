package com.example.backend_uppgift;

import com.example.backend_uppgift.Services.ShipperService;
import com.example.backend_uppgift.Utils.StreamProvider;
import com.example.backend_uppgift.models.Shipper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FetchShippersTest {

    @Mock
    private ShipperService shipperService;
    @Mock
    private StreamProvider streamProvider;
    @InjectMocks
    private FetchShippers sut;

    private ObjectMapper objectMapper;
    @BeforeEach
    public void setUp() throws Exception{
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testGetShippers()throws Exception{

        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("shipper.json");
        if (resourceStream == null) {
            throw new IllegalArgumentException("File not found! Check the file path and name.");
        }

        String json = new Scanner(resourceStream, StandardCharsets.UTF_8).useDelimiter("\\A").next();

        when(streamProvider.getDataStreamShippers()).thenReturn(json);

        List<Shipper> capturedShippers = sut.getShippers();

        assertEquals(2, capturedShippers.size());

        assertEquals("Telia", capturedShippers.get(0).getCompanyName());
        assertEquals("070-569-3764", capturedShippers.get(0).getPhone());

        assertEquals("Volvo", capturedShippers.get(1).getCompanyName());
        assertEquals("lars.aslund@hotmail.com", capturedShippers.get(1).getEmail());

    }

}
