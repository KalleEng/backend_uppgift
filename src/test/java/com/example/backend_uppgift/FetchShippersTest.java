package com.example.backend_uppgift;

import com.example.backend_uppgift.Services.ShipperService;
import com.example.backend_uppgift.Utils.StreamProvider;
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

import java.io.InputStream;
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
    @Mock
    private StreamProvider streamProvider;

    @BeforeEach
    public void setUp() throws Exception{
        sut = new FetchShippers(shipperService, streamProvider);
    }

    @Test
    public void testFetchShippersMappingCorrectly()throws Exception{

        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("shipper.json");
        when(streamProvider.getDataStreamShippers()).thenReturn(resourceStream.toString());

        List<Shipper> capturedShippers = sut.getShippers();
        assertEquals(2, capturedShippers.size());

        assertEquals("Telia", capturedShippers.get(0).getCompanyName());
        assertEquals("070-569-3764", capturedShippers.get(0).getPhone());

        assertEquals("Volvo", capturedShippers.get(1).getCompanyName());
        assertEquals("lars.aslund@hotmail.com", capturedShippers.get(1).getEmail());

    }
}
