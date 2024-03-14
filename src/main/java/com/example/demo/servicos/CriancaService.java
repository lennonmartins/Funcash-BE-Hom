package com.example.demo.servicos;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controladores.CriancaRequestDTO;
import com.example.demo.controladores.CriancaResponseDTO;
import com.example.demo.mappers.CriancaMapper;
import com.example.demo.modelos.Crianca;
import com.example.demo.modelos.usuario.Usuario;
import com.example.demo.repositorios.CriancaRepository;
import com.example.demo.utils.DataConvert;

import jakarta.validation.Valid;

@Service
public class CriancaService {
    @Autowired
    private CriancaRepository criancaRepository;

    @Autowired
    private CriancaMapper criancaMapper;

    public CriancaResponseDTO cadastrar(@Valid CriancaRequestDTO criancaRequestDTO) throws Exception {
        Crianca crianca = criancaMapper.criancaRequestparaCrianca(criancaRequestDTO);
        criancaRepository.save(crianca);
        return criancaMapper.criancaParaCriancaResponseDTO(crianca);
    }

    public CriancaResponseDTO buscarPorId(Long id) {
        return criancaMapper.criancaParaCriancaResponseDTO(buscarCriancaPeloId(id));
    }

    private Crianca buscarCriancaPeloId(Long id) {
        Optional<Crianca> criancaOptional = criancaRepository.findById(id);

        return criancaOptional.orElseThrow(NoSuchElementException::new);
    }

    public Collection<CriancaResponseDTO> buscarTodas() {
        return criancaMapper.criancasParaCriancasResponsesDtos((Collection<Crianca>) criancaRepository.findAll());
    }

    public Collection<CriancaResponseDTO> buscarCriancasPeloResponsavel(long id) {
        Collection<Crianca> criancaRetornadas = criancaRepository.findAllByResponsavel(id);
        return criancaMapper.criancasParaCriancasResponsesDtos(criancaRetornadas);
    }

    public CriancaResponseDTO alterar(@Valid CriancaRequestDTO criancaRequestDTO, Long id) {
        Crianca criancaParaAlterar = buscarCriancaPeloId(id);
        criancaParaAlterar.setNome(criancaRequestDTO.getNome());
        criancaParaAlterar.setGenero(criancaRequestDTO.getGenero());
        criancaParaAlterar.setDataDeNascimento(DataConvert.obterData(criancaRequestDTO.getDataDeNascimento()));
        criancaParaAlterar.setUsuario(
                new Usuario(criancaRequestDTO.getEmail(), criancaRequestDTO.getSenha(), criancaRequestDTO.getRole()));
        criancaParaAlterar.setApelido(criancaRequestDTO.getApelido());
        criancaParaAlterar.setFoto(criancaRequestDTO.getFoto());

        criancaRepository.save(criancaParaAlterar);

        return criancaMapper.criancaParaCriancaResponseDTO(criancaParaAlterar);
    }

}
