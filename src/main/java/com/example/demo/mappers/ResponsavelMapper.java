package com.example.demo.mappers;

import com.example.demo.dto.ResponsavelRequestDTO;
import com.example.demo.dto.ResponsavelResponseDTO;
import com.example.demo.modelos.Responsavel;

public interface ResponsavelMapper {
    public ResponsavelResponseDTO responsavelParaResponsavelResponseDTO(Responsavel responsavel);

    public Responsavel responsavelRequestparaResponsavel(ResponsavelRequestDTO responsavelRequestDTO) throws Exception;
}
