package com.example.demo.modelos;

import java.time.LocalDate;

import com.example.demo.utils.EntidadeBase;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@MappedSuperclass
@Setter @Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa extends EntidadeBase {
        @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataDeNascimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @Lob
    @Column(nullable = true, length = 16777215)
    private String foto;


    public Pessoa(String nome, LocalDate dataDeNascimento, Genero genero, String foto) throws Exception {
        validaDataDeNAscimento(dataDeNascimento);
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.genero = genero;
        this.foto = foto;
    }

    private void validaDataDeNAscimento(LocalDate dataDeNascimetno) throws Exception {
        if (dataDeNascimetno == null) {
            throw new Exception("A data de nascimento não pode ser vazia");
        }
    }
}
