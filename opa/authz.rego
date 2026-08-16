package demo.authz

default allow := false

permissions := {
    "viewer": {
        "GET": ["/api/documents"]
    },

    "editor": {
        "GET": ["/api/documents"],
        "POST": ["/api/documents"]
    }
}

allow if {
    input.role == "admin"
}

allow if {
    input.path in permissions[input.role][input.method]
}