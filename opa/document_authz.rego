package documents.authz

default allow := false

allow if {
    input.role == "admin"
}

allow if {
    input.role == "editor"
    input.method == "GET"
    input.path == "/api/documents"
}

allow if {
    input.role == "editor"
    input.method == "POST"
    input.path == "/api/documents"
}

allow if {
    input.role == "viewer"
    input.method == "GET"
    input.path == "/api/documents"
}