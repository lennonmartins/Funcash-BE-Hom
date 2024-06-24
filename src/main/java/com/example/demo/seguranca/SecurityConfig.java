package com.example.demo.seguranca;

import org.mindrot.jbcrypt.BCrypt;


public class SecurityConfig {
    public String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}