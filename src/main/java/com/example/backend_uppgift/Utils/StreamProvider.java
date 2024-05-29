package com.example.backend_uppgift.Utils;

import com.example.backend_uppgift.Configurations.IntegrationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
@Service
public class StreamProvider {

    @Qualifier("integrationProperties")
    @Autowired
    IntegrationProperties properties;

    public String getDataStreamContractCustomers()throws IOException {
        return properties.getContractCustomerProperties().getUrl();
    }

    public String getDataStreamShippers()throws IOException{
        return properties.getShipperProperties().getUrl();
    }

    public String getDataStreamBlacklist() throws IOException{
        return properties.getBlacklistProperties().getUrl();
    }

    public String getDataStreamBlacklistCheck() throws IOException{
        return properties.getBlacklistProperties().getCheck();
    }
}
