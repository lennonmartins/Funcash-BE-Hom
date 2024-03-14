package com.example.demo.mappers;

import java.util.Collection;

import com.example.demo.dto.TarefaRequestDTO;
import com.example.demo.dto.TarefaResponseDTO;
import com.example.demo.modelos.Tarefa;

public interface TarefaMapper {
    public TarefaResponseDTO tarefaParaTarefaResponseDTO(Tarefa tarefa);

    public Tarefa tarefaRequestparaTarefa(TarefaRequestDTO tarefaRequestDTO);

    public Collection<TarefaResponseDTO> tarefasParaTarefasResponsesDtos(Collection<Tarefa> tarefas);
}
