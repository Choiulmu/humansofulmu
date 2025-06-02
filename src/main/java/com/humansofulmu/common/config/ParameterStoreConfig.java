package com.humansofulmu.common.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;

@Configuration
@RequiredArgsConstructor
public class ParameterStoreConfig {

    @Autowired
    private SsmClient ssmClient;

    @Value("${app.env}")
    private String env;

    private final String DEFAULT = "/hou";

    public String getParam(String keyword) {
        String endpoint = DEFAULT + "/" + env + "/" + keyword;
        GetParameterRequest request = createParameterRequest(endpoint);
        GetParameterResponse response = ssmClient.getParameter(request);
        return response.parameter().value();
    }

    private GetParameterRequest createParameterRequest(String endpoint) {
        return GetParameterRequest.builder()
                .name(endpoint)
                .build();
    }

}
