package com.example.demo.seguranca;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    private final TokenService tokenService;

    public FilterConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        List<String> excludedUrls = Arrays.asList(
                "/api/v1/login",
                "/api/v1/responsavel"
        );

        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter(tokenService, excludedUrls));
        registrationBean.addUrlPatterns("/api/v1/*");
        return registrationBean;
    }
}