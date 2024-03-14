package com.example.demo.servicos;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DesejoRequestDTO;
import com.example.demo.dto.DesejoResponseDTO;
import com.example.demo.mappers.DesejoMapper;
import com.example.demo.modelos.Desejo;
import com.example.demo.repositorios.DesejoRepository;

import jakarta.validation.Valid;

@Service
public class DesejoService {

    @Autowired
    private DesejoRepository desejoRepository;

    @Autowired
    private DesejoMapper desejoMapper;

    public DesejoResponseDTO cadastrar(@Valid DesejoRequestDTO desejoRequestDTO) throws Exception {
        Desejo desejo = desejoMapper.desejoRequestDTOParaDesejo(desejoRequestDTO);
        desejoRepository.save(desejo);
        return desejoMapper.desejoParaDesejoResponseDTO(desejo);
    }

    public DesejoResponseDTO buscarPorId(Long id) {
        return desejoMapper.desejoParaDesejoResponseDTO(buscarDesejoPeloId(id));
    }

    public Collection<DesejoResponseDTO> buscarTodos() {
        return desejoMapper.desejoParaDesejosResponsesDtos((Collection<Desejo>) desejoRepository.findAll());
    }

    public DesejoResponseDTO alterar(DesejoRequestDTO desejoRequestDTO, Long id) {
        Desejo desejoParaAlterar = buscarDesejoPeloId(id);
        desejoParaAlterar.setTitulo(desejoRequestDTO.getNome());
        desejoParaAlterar.setDescricao(desejoRequestDTO.getDescricao());
        desejoParaAlterar.setValor(desejoRequestDTO.getValor());

        desejoRepository.save(desejoParaAlterar);

        return desejoMapper.desejoParaDesejoResponseDTO(desejoParaAlterar);
    }

    private Desejo buscarDesejoPeloId(Long id) {
        Optional<Desejo> desejoOptional = desejoRepository.findById(id);
        if (desejoOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return desejoOptional.get();
    }

}
