package com.example.demo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UsuarioResponseDto;
import com.example.demo.servicos.AuthService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = { "/api/v1/autenticacao" }, produces = { "application/json" })
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(path = { "/entrar" })
    public ResponseEntity<UsuarioResponseDto> autenticar(@Valid @RequestBody LoginDTO loginRequest) {
        UsuarioResponseDto responseDTO = authService.login(loginRequest.getEmail(), loginRequest.getSenha());
        return ResponseEntity.ok(responseDTO);
    }
}
