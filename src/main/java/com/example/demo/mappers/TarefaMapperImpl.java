package com.example.demo.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.TarefaRequestDTO;
import com.example.demo.dto.TarefaResponseDTO;
import com.example.demo.modelos.Crianca;
import com.example.demo.modelos.Tarefa;
import com.example.demo.repositorios.CriancaRepository;
import com.example.demo.utils.DataConvert;

@Component
public class TarefaMapperImpl implements TarefaMapper{

    @Autowired
    private CriancaRepository criancaRepository;

    @Override
    public TarefaResponseDTO tarefaParaTarefaResponseDTO(Tarefa tarefa) {
        return new TarefaResponseDTO(tarefa.getId(),
                tarefa.getHoraLimite(),
                tarefa.getDataLimite(),
                tarefa.getDataDeCriacao(),
                tarefa.getValor(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getStatus(),
                tarefa.getCrianca().getId()
                );
    }

    @Override
    public Tarefa tarefaRequestparaTarefa(TarefaRequestDTO tarefaRequestDTO) {
        Crianca crianca = verificaSeObjetoEhNulo(tarefaRequestDTO);
        return new Tarefa(
        DataConvert.obterHoraLimiteCompleta(tarefaRequestDTO.getDataLimite(), 
        tarefaRequestDTO.getHoraLimite()),
        DataConvert.obterData(tarefaRequestDTO.getDataLimite()),
        tarefaRequestDTO.getValor(),
        tarefaRequestDTO.getNome(),   
        tarefaRequestDTO.getDescricao(),
        crianca
        );
    }

    @Override
    public Collection<TarefaResponseDTO> tarefasParaTarefasResponsesDtos(Collection<Tarefa> tarefas) {
        Collection<TarefaResponseDTO> tarefasResponsesDtos = new ArrayList<>();

       for(Tarefa tarefa : tarefas){
        tarefasResponsesDtos.add(tarefaParaTarefaResponseDTO(tarefa));
       }
       return tarefasResponsesDtos;
    }

    private Crianca verificaSeObjetoEhNulo(TarefaRequestDTO tarefaRequestDTO) {
        Optional<Crianca> criancaOptional = criancaRepository.findById(tarefaRequestDTO.getIdDaCrianca());
        if(criancaOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        Crianca crianca = criancaOptional.get();
        return crianca;
    }    
}
