package com.humansofulmu.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;

@Configuration
public class ClientConfig {

    @Bean(name = "ssmClient")
    public SsmClient ssmClient() {
        return SsmClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .build();
    }
}
