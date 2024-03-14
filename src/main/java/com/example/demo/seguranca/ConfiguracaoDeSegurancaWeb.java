package com.example.demo.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource("classpath:application.properties")
public class ConfiguracaoDeSegurancaWeb {

    @Autowired
    FiltroDeSeguranca filtroDeSeguranca;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/").permitAll();
                    authorize.requestMatchers("/swagger-ui/index.html").permitAll();
                    authorize.requestMatchers("/swagger-ui/swagger-ui.css").permitAll();
                    authorize.requestMatchers("/swagger-ui/index.css").permitAll();
                    authorize.requestMatchers("/swagger-ui/swagger-ui-bundle.js").permitAll();
                    authorize.requestMatchers("/swagger-ui/swagger-ui-standalone-preset.js").permitAll();
                    authorize.requestMatchers("/swagger-ui/swagger-initializer.js").permitAll();
                    authorize.requestMatchers("/swagger-ui/favicon-32x32.png").permitAll();
                    authorize.requestMatchers("/swagger-ui/favicon-16x16.png").permitAll();
                    authorize.requestMatchers("/v3/api-docs/swagger-config").permitAll();
                    authorize.requestMatchers("/api/v1/autenticacao/entrar").permitAll();
                    authorize.requestMatchers("/api/v1/responsavel").permitAll();
                    authorize.anyRequest().authenticated();
                    })
                .cors().and()
                .csrf().disable()
                .addFilterBefore(filtroDeSeguranca, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
