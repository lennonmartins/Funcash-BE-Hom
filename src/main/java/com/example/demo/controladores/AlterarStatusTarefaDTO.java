package com.example.demo.controladores;

import com.example.demo.dto.TarefaRequestDTO;
import com.example.demo.dto.UsuarioRequestDTO;

import lombok.Getter;

@Getter
public class AlterarStatusTarefaDTO {
    private TarefaRequestDTO tarefaRequestDTO;
    private UsuarioRequestDTO usuarioRequestDTO;
}
