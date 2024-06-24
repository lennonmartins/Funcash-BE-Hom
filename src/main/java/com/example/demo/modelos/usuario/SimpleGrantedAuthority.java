package com.example.demo.modelos.usuario;

public class SimpleGrantedAuthority implements GrantedAuthority{

    private final String authority;

    public SimpleGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }   
}