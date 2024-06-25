package com.example.demo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfigurations {
   @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        org.springframework.web.cors.CorsConfiguration config = new CorsConfiguration();
        
        // Configurar os cabeçalhos de CORS
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://funcash.vercel.app");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("TRACE");
        config.addAllowedMethod("CONNECT");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
