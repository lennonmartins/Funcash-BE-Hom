package com.example.demo.repositorios;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Todo;
import com.example.demo.modelos.Crianca;

public interface CriancaRepository extends JpaRepository<Todo, Long> {
    List<Crianca> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT c FROM Crianca c JOIN c.responsavel r WHERE r.id = :idDoResponsavel")
    Collection<Crianca> findAllByResponsavel(Long idDoResponsavel);
}
