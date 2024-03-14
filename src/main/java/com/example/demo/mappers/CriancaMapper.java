package com.example.demo.mappers;

import java.util.Collection;

import com.example.demo.dto.CriancaRequestDTO;
import com.example.demo.dto.CriancaResponseDTO;
import com.example.demo.modelos.Crianca;

public interface CriancaMapper {
    public CriancaResponseDTO criancaParaCriancaResponseDTO(Crianca crianca);  
    public Crianca criancaRequestparaCrianca(CriancaRequestDTO criancaRequestDTO) throws Exception;
    public Collection<CriancaResponseDTO> criancasParaCriancasResponsesDtos(Collection<Crianca> criancas);
}
