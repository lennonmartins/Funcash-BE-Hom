package com.example.demo.modelos;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.modelos.usuario.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter @Setter
public class Responsavel extends Pessoa {
    @Column(nullable = false, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.REMOVE)
    private List<Crianca> criancas;

    @OneToOne(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private Usuario usuario;

    public Responsavel(Usuario usuario, String nome, String cpf, LocalDate dataDeNascimentoResponsavel, Genero genero,
            String foto) throws Exception {
        super(nome, dataDeNascimentoResponsavel, genero, foto);
        this.cpf = cpf;
        setUsuario(usuario);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        usuario.vincularResponsavel(this);
    }

    public Tarefa concluirTarefa(Tarefa tarefaParaAlterar, StatusDaTarefa status) throws Exception {
        tarefaParaAlterar.marcarComoConcluida(status, this);
        var criancaDaTarefa = tarefaParaAlterar.getCrianca();
        criancaDaTarefa.atribuirSaldo(tarefaParaAlterar.getValor(), this.usuario);
        return tarefaParaAlterar;
    }
}
