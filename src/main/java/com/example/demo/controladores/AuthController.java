package com.example.demo.controladores;

import org.apache.commons.codec.EncoderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UsuarioResponseDto;
import com.example.demo.servicos.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
// @CrossOrigin(origins = "*")
@RequestMapping(path = { "/api/v1" }, produces = { "application/json" })
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // @Autowired
    // AuthService authService;

    @Operation(summary = "Acessar o sistema")
    @ApiResponse(responseCode = "200")
    //@PostMapping(consumes = { "application/json" })
    @PostMapping(path = { "/autenticacao/entrar" })
    public ResponseEntity<UsuarioResponseDto> autenticar(@Valid @RequestBody LoginDTO loginRequest) throws EncoderException {
        UsuarioResponseDto responseDTO = authService.login(loginRequest.getEmail(), loginRequest.getSenha());
        return ResponseEntity.ok(responseDTO);
    }
}
