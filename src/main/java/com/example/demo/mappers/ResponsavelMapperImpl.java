package com.example.demo.mappers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.dto.ResponsavelRequestDTO;
import com.example.demo.dto.ResponsavelResponseDTO;
import com.example.demo.modelos.Responsavel;
import com.example.demo.modelos.usuario.Usuario;
import com.example.demo.utils.DataConvert;

public class ResponsavelMapperImpl implements ResponsavelMapper{

    @Override
    public ResponsavelResponseDTO responsavelParaResponsavelResponseDTO(Responsavel responsavel) {
        return new ResponsavelResponseDTO(responsavel.getId(),
                responsavel.getNome(),
                responsavel.getUsuario().getEmail(),
                responsavel.getCpf(),
                responsavel.getDataDeNascimento(),
                responsavel.getGenero(),
                responsavel.getUsuario().getSenha(),
                responsavel.getFoto());
    }

    @Override
    public Responsavel responsavelRequestparaResponsavel(ResponsavelRequestDTO responsavelRequestDTO) throws Exception {
         String senhaCriptografada = new BCryptPasswordEncoder().encode(responsavelRequestDTO.getSenha());
        return new Responsavel(
                new Usuario(responsavelRequestDTO.getEmail(), senhaCriptografada, responsavelRequestDTO.getRole()),
                responsavelRequestDTO.getNome(),
                responsavelRequestDTO.getCpf(),
                DataConvert.obterData(responsavelRequestDTO.getDataDeNascimentoResponsavel()),
                responsavelRequestDTO.getGenero(),
                responsavelRequestDTO.getFoto());
    }
    
}
