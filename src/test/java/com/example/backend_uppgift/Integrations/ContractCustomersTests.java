package com.example.backend_uppgift.Integrations;

import com.example.backend_uppgift.FetchContractCustomers;
import com.example.backend_uppgift.Services.ContractCustomerService;
import com.example.backend_uppgift.Utils.StreamProvider;
import com.example.backend_uppgift.models.ContractCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ContractCustomersTests {

    private StreamProvider streamProvider = mock(StreamProvider.class);
    private ContractCustomerService contractCustomerService = mock(ContractCustomerService.class);
    FetchContractCustomers sut;

    @BeforeEach
    void setUp() {
        sut = new FetchContractCustomers(contractCustomerService, streamProvider);
    }

    @Test
    void whenFetchContractCustomersShouldMapCorrectly() throws IOException {
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("ContractCustomers.xml");
        when(streamProvider.getDataStreamContractCustomers()).thenReturn(resourceStream.toString());

        List<ContractCustomer> result = sut.getContractCustomers();

        assertEquals(3, result.size());
        assertEquals("Maria Åslund", result.get(0).contactName);
        assertEquals("gardener", result.get(0).contactTitle);
        assertEquals("Kramland", result.get(0).city);
    }
}
