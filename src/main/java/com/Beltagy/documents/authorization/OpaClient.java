package com.Beltagy.documents.authorization;

import com.Beltagy.documents.OpaInput;
import com.Beltagy.documents.OpaRequest;
import com.Beltagy.documents.OpaResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;


@Component
@Slf4j
public class OpaClient {
    private final RestClient restClient;

    public OpaClient(RestClient.Builder restClientBuilder,
                     OpaProperties properties) {
        this.restClient = restClientBuilder
                .baseUrl(properties.baseUrl())
                .build();
    }

    public boolean isAllowed(String role, String method, String path) {
        OpaInput input = new OpaInput(role, method, path);
        OpaRequest request = new OpaRequest(input);
        try {
            OpaResponse response = restClient.post()
                    .uri("/v1/data/documents/authz/allow")
                    .body(request)
                    .retrieve()
                    .body(OpaResponse.class);
            return response != null && Boolean.TRUE.equals(response.result());
        } catch (RestClientException exception) {
            log.error(
                    "OPA authorization request failed for role={}, method={}, path={}",
                    role,
                    method,
                    path,
                    exception
            );
            return false;
        }
    }
}
