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

    public InputStream getDataStreamContractCustomers()throws IOException {
        URL url = new URL(properties.getContractCustomerProperties().getUrl());
        return url.openStream();
    }

    public InputStream getDataStreamShippers()throws IOException{
        URL url = new URL(properties.getShipperProperties().getUrl());
        return url.openStream();
    }

    public InputStream getDataStreamBlacklist() throws IOException{
        URL url = new URL(properties.getBlacklistProperties().getUrl());
        return url.openStream();
    }

    public String getRabbitHost(){
        return properties.getRabbitMQProperties().getHost();
    }

    public String getRabbitUsername(){
        return properties.getRabbitMQProperties().getUsername();
    }

    public String getRabbitPassword(){
        return properties.getRabbitMQProperties().getPassword();
    }

    public String getRabbitQueueName(){
        return properties.getRabbitMQProperties().getQueueName();
    }
}
