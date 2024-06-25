package com.example.demo.controladores;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TarefaRequestDTO;
import com.example.demo.dto.TarefaResponseDTO;
import com.example.demo.servicos.TarefaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "https://funcash.vercel.app")
@RequestMapping(path = { "api/v1/tarefas" }, produces = { "application/json" })
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @Operation(summary = "Cadastrar uma nova tarefa")
    @ApiResponse(responseCode = "201")
    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<TarefaResponseDTO> cadastrarTarefa(@RequestBody @Valid TarefaRequestDTO tarefaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.cadastrar(tarefaRequestDTO));
    }

    @DeleteMapping(path = "/{id}")
    public void remover(@PathVariable Long id) {
        tarefaService.deletar(id);
    }

    @Operation(summary = "Buscar uma lista das tarefas")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas cadastradas")
    @GetMapping
    public ResponseEntity<Collection<TarefaResponseDTO>> buscarTodas() {
        return ResponseEntity.ok(tarefaService.buscarTodas());
    }

    @Operation(summary = "Buscar uma tarefa pelo seu id")
    @ApiResponse(responseCode = "200")
    @GetMapping(path = "/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @Operation(summary = "Atualizar uma tarefa")
    @ApiResponse(responseCode = "200")
    @PutMapping(path = "/{idTarefa}", consumes = { "application/json" })
    public ResponseEntity<TarefaResponseDTO> alteraTarefa(@RequestBody @Valid TarefaRequestDTO tarefaRequestDTO,
            @PathVariable Long idTarefa) {
        return ResponseEntity.ok(tarefaService.alterar(tarefaRequestDTO, idTarefa));
    }

    @Operation(summary = "Buscar tarefas pelo id da criança")
    @GetMapping(path = "/crianca/{idDaCrianca}/tarefas")
    public ResponseEntity<Collection<TarefaResponseDTO>> buscarPeloIdCrianca(@PathVariable long idDaCrianca) {
        return ResponseEntity.ok(tarefaService.buscarTarefasPelaCrianca(idDaCrianca));
    }

    @Operation(summary = "Altera o status da tarefa para realizada")
    @PutMapping(path = "/crianca/{idTarefa}/tarefas", consumes = { "application/json" })
    public ResponseEntity<TarefaResponseDTO> alterarStatusTarefa(
            @RequestBody @Valid AlterarStatusTarefaDTO alteraStatusTarefaRequestDTO, @PathVariable Long idTarefa)
            throws Exception {
        return ResponseEntity.ok(tarefaService.alterarStatusTarefa(alteraStatusTarefaRequestDTO, idTarefa));
    }
}
