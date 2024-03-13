package com.example.demo.repositorios;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.modelos.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT t FROM Tarefa t JOIN t.crianca c WHERE c.id = :idDaCrianca")
    Collection<Tarefa> findAllByCrianca(Long idDaCrianca);

}
