package com.Beltagy.documents.authorization;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final OpaClient opaClient;

    public AuthorizationService(OpaClient opaClient) {
        this.opaClient = opaClient;
    }
    public boolean isAllowed(
            String role,
            String method,
            String path
    ){
        return opaClient.isAllowed(role, method, path);
    }
}
