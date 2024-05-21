package com.example.backend_uppgift.Utils;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
@Service
public class StreamProvider {
    public InputStream getDataStreamContractCustomers()throws IOException {
        URL url = new URL("https://javaintegration.systementor.se/customers");
        return url.openStream();
    }
}
