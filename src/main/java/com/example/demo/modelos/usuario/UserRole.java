package com.example.demo.modelos.usuario;

public enum UserRole {
    RESPONSAVEL("responsavel"),
    CRIANCA("crianca");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
