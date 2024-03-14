package com.example.demo.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.DesejoRequestDTO;
import com.example.demo.dto.DesejoResponseDTO;
import com.example.demo.modelos.Crianca;
import com.example.demo.modelos.Desejo;
import com.example.demo.repositorios.CriancaRepository;

@Component
public class DesejoMapperImpl implements DesejoMapper {

    @Autowired
    private CriancaRepository criancaRepository;

    @Override
    public Desejo desejoRequestDTOParaDesejo(DesejoRequestDTO desejoRequestDTO) throws Exception {
        Crianca crianca = verificaSeObjetoEhNulo(desejoRequestDTO);
        return new Desejo(desejoRequestDTO.getNome(),
                desejoRequestDTO.getDescricao(),
                desejoRequestDTO.getValor(),
                crianca);
    }

    private Crianca verificaSeObjetoEhNulo(DesejoRequestDTO desejoRequestDTO) {
        Optional<Crianca> criancaOptional = criancaRepository.findById(desejoRequestDTO.getIdDaCrianca());
        if (criancaOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        Crianca crianca = criancaOptional.get();
        return crianca;
    }

    @Override
    public DesejoResponseDTO desejoParaDesejoResponseDTO(Desejo desejo) {
        return new DesejoResponseDTO(
                desejo.getId(),
                desejo.getTitulo(),
                desejo.getDescricao(),
                desejo.getValor());
    }

    @Override
    public Collection<DesejoResponseDTO> desejoParaDesejosResponsesDtos(Collection<Desejo> desejos) {
        Collection<DesejoResponseDTO> desejoResponseDTOs = new ArrayList<>();

        for (Desejo desejo : desejos) {
            desejoResponseDTOs.add(desejoParaDesejoResponseDTO(desejo));
        }
        return desejoResponseDTOs;
    }
}
