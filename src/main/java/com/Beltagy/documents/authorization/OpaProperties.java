package com.Beltagy.documents.authorization;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "opa")
public record OpaProperties(
        String baseUrl
) {
}
