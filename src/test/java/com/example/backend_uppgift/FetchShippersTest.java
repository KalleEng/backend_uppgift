/*
package com.example.backend_uppgift;

import com.example.backend_uppgift.Services.ShipperService;
import com.example.backend_uppgift.models.Shipper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FetchShippersTest {
    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ShipperService shipperService;
    @InjectMocks
    private FetchShippers sut;

    private Shipper[] shippers;

    @BeforeEach
    public void setUp() throws Exception{
        sut = new FetchShippers()
    }

    @Test
    public void testRun()throws Exception{
        fetchShippers.run();

        ArgumentCaptor<Shipper> shipperCaptor = ArgumentCaptor.forClass(Shipper.class);
        verify(shipperService, times(2)).saveShipper(shipperCaptor.capture());

        List<Shipper> capturedShippers = shipperCaptor.getAllValues();
        assertEquals(2, capturedShippers.size());

        assertEquals("Telia", capturedShippers.get(0).getCompanyName());
        assertEquals("7776655", capturedShippers.get(0).getPhone());
        assertEquals("Volvo", capturedShippers.get(1).getCompanyName());
        assertEquals("8883322", capturedShippers.get(1).getPhone());
    }
}*/
