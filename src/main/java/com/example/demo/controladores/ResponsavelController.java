package com.example.demo.controladores;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponsavelRequestDTO;
import com.example.demo.dto.ResponsavelResponseDTO;
import com.example.demo.repositorios.ResponsavelRepository;
import com.example.demo.servicos.ResponsavelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = { "http://localhost:5173/", "*" }, maxAge = 3600, allowCredentials = "true")
@RequestMapping(path = { "/api/v1/responsavel" }, produces = { "application/json" })
public class ResponsavelController {
    private final ResponsavelService responsavelService;

    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Operation(summary = "Cadastrar um novo responsavel")
    @ApiResponse(responseCode = "201")
    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<ResponsavelResponseDTO> cadastrar(
            @RequestBody @Valid ResponsavelRequestDTO responsavelRequestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(responsavelService.cadastrar(responsavelRequestDTO));
    }

    @Operation(summary = "Buscar um responsavel pelo seu id")
    @ApiResponse(responseCode = "200", description = "Retorna a responsavel solicitada")
    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('RESPONSAVEL')")
    public ResponseEntity<ResponsavelResponseDTO> buscarPorId(@PathVariable Long id) throws NameNotFoundException {
        return ResponseEntity.ok(responsavelService.buscarPorId(id));
    }

    @Operation(summary = "Altera os dados de um responsavel cadastrado")
    @ApiResponse(responseCode = "200")
    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('RESPONSAVEL')")
    public ResponseEntity<ResponsavelResponseDTO> alterarDadosDoResponsavel(
            @RequestBody @Valid ResponsavelRequestDTO responsavelRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(responsavelService.alterarReponsavel(responsavelRequestDTO, id));
    }

    @PreAuthorize("hasRole('RESPONSAVEL')")
    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        responsavelRepository.deleteById(id);
    }
}
