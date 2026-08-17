package com.Beltagy.documents.configurations;

import com.Beltagy.documents.authorization.OpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OpaProperties.class)
public record OpaConfiguration() {
}
