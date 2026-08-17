package com.Beltagy.documents.configurations;

import com.Beltagy.documents.authorization.OpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(OpaProperties.class)
public class OpaConfiguration {
    @Bean
    public RestClient OpaClient(
            RestClient.Builder restClientBuilder,
            OpaProperties properties) {

        return restClientBuilder
                .baseUrl(properties.baseUrl())
                .build();
    }
}
