package com.example.demo.mappers;

import java.util.Collection;

import com.example.demo.dto.DesejoRequestDTO;
import com.example.demo.dto.DesejoResponseDTO;
import com.example.demo.modelos.Desejo;

public interface DesejoMapper {

    Desejo desejoRequestDTOParaDesejo(DesejoRequestDTO desejoRequestDTO) throws Exception;

    DesejoResponseDTO desejoParaDesejoResponseDTO(Desejo desejo);

    Collection<DesejoResponseDTO> desejoParaDesejosResponsesDtos(Collection<Desejo> all);

}
