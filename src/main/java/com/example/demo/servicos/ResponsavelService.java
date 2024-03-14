package com.example.demo.servicos;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResponsavelRequestDTO;
import com.example.demo.dto.ResponsavelResponseDTO;
import com.example.demo.mappers.ResponsavelMapper;
import com.example.demo.modelos.Responsavel;
import com.example.demo.modelos.usuario.Usuario;
import com.example.demo.repositorios.ResponsavelRepository;
import com.example.demo.repositorios.UsuarioRepository;
import com.example.demo.utils.DataConvert;

import jakarta.validation.Valid;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private ResponsavelMapper responsavelMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponsavelResponseDTO cadastrar(@Valid ResponsavelRequestDTO responsavelRequestDTO) throws Exception {
        Responsavel responsavel = responsavelMapper.responsavelRequestparaResponsavel(responsavelRequestDTO);
        responsavelRepository.save(responsavel);
        responsavel.setUsuario(responsavel.getUsuario());
        usuarioRepository.save(responsavel.getUsuario());
        return responsavelMapper.responsavelParaResponsavelResponseDTO(responsavel);
    }

    public ResponsavelResponseDTO buscarPorId(Long id) {
        return responsavelMapper.responsavelParaResponsavelResponseDTO(buscarResponsavelPeloId(id));
    }

    public ResponsavelResponseDTO alterarReponsavel(@Valid ResponsavelRequestDTO responsavelRequestDTO, Long id) {
        Usuario usuarioParaAlterar = usuarioRepository.findByIdResponsavel(id);
        usuarioParaAlterar.setEmail(responsavelRequestDTO.getEmail());
        usuarioParaAlterar.setSenha(responsavelRequestDTO.getSenha());
        Responsavel responsavelParaAlterar = buscarResponsavelPeloId(id);
        responsavelParaAlterar.setNome(responsavelRequestDTO.getNome());
        responsavelParaAlterar.setGenero(responsavelRequestDTO.getGenero());
        responsavelParaAlterar.setFoto(responsavelRequestDTO.getFoto());
        responsavelParaAlterar
                .setDataDeNascimento(DataConvert.obterData(responsavelRequestDTO.getDataDeNascimentoResponsavel()));
        responsavelParaAlterar.setUsuario(usuarioParaAlterar);

        responsavelRepository.save(responsavelParaAlterar);

        return responsavelMapper.responsavelParaResponsavelResponseDTO(responsavelParaAlterar);
    }

    private Responsavel buscarResponsavelPeloId(Long id) {
        Optional<Responsavel> responsavelOptional = responsavelRepository.findById(id);
        if (responsavelOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return responsavelOptional.get();
    }
}
