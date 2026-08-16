package com.Beltagy.opademo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class OpaClient {
    private final RestClient restClient;
    public OpaClient(RestClient.Builder restClientBuilder,
                     @Value("${opa.base-url}") String opaBaseUrl){
        this.restClient = restClientBuilder
                .baseUrl(opaBaseUrl)
                .build();
    }

    public boolean isAllowed(String role, String method, String path){
        OpaInput input = new OpaInput(role,method,path);
        OpaRequest request = new OpaRequest(input);
        OpaResponse response = restClient.post()
                .uri("/v1/data/demo/authz/allow")
                .body(request)
                .retrieve()
                .body(OpaResponse.class);
        return response !=null && response.result();
    }
}
