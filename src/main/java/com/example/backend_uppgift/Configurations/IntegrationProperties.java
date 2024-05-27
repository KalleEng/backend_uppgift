package com.example.backend_uppgift.Configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;


@ConfigurationPropertiesScan
@Configuration
@ConfigurationProperties(prefix = "integrations")
@Data
public class IntegrationProperties {
    private ContractCustomerProperties contractCustomerProperties;
    private ShipperProperties shipperProperties;
    private BlacklistProperties blacklistProperties;
    private RabbitMQProperties rabbitMQProperties;
}
