package com.example.demo.modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.modelos.usuario.UserRole;
import com.example.demo.utils.EntidadeBase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tarefa extends EntidadeBase {
    @Column(nullable = false)
    private LocalDateTime horaLimite;

    @Column(nullable = false)
    private LocalDate dataLimite;

    @Column(nullable = false)
    private LocalDateTime dataDeCriacao;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = true, length = 250)
    private String descricao;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_crianca", nullable = true)
    private Crianca crianca;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDaTarefa status;

    public Tarefa(LocalDateTime horaLimite, LocalDate dataLimite, double valor, String titulo, String descricao,
            Crianca crianca) {
        this.horaLimite = horaLimite;
        this.dataLimite = dataLimite;
        this.valor = valor;
        this.titulo = titulo;
        this.dataDeCriacao = LocalDateTime.now();
        this.descricao = descricao;
        this.crianca = crianca;
        this.status = status.A_FAZER;
    }

    public void marcarComoConcluida(StatusDaTarefa status, Responsavel responsavel) throws Exception {
        boolean usuarioResponsavel = responsavel.getUsuario().getRole().equals(UserRole.RESPONSAVEL);

        if (!usuarioResponsavel) {
            throw new Exception("Você não pode atualizar o status para concluído!");
        }

        if (this.status == status) {
            throw new Exception("Esta tarefa já esta concluída!");
        }
        this.setStatus(status);
    }
}
