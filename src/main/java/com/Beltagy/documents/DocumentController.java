package com.Beltagy.documents;

import com.Beltagy.documents.authorization.AuthorizationService;
import com.Beltagy.documents.authorization.OpaClient;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private final AuthorizationService authorizationService;

    public DocumentController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public List<String> getDocuments(
            @RequestHeader("X-User-Role") String role
            , HttpServletRequest request) {

        authorize(role, request);

        return List.of(
                "doc1"
                , "doc2"
                , "doc3"
        );
    }

    @PostMapping
    public String createDocument(
            @RequestHeader("X-User-Role") String role,
            HttpServletRequest request
    ) {
        authorize(role, request);
        return "doc-created";
    }

    @DeleteMapping("/{id}")
    public String deleteDocument(
            @PathVariable String id,
            @RequestHeader("X-User-Role") String role,
            HttpServletRequest request
    ) {
        authorize(role, request);
        return "deleted-document-" + id;
    }

    private void authorize(String role, HttpServletRequest request) {
        boolean allowed = authorizationService.isAllowed(
                role,
                request.getMethod(),
                request.getRequestURI()
        );
        if (!allowed)
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Access denied " // the caller shouldn't know the authorization technoloty
            );

    }
}
