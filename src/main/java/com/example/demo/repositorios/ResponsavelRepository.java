package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Todo;
import com.example.demo.modelos.Responsavel;

public interface ResponsavelRepository extends JpaRepository<Todo, Long> {
    List<Responsavel> findByNomeContainingIgnoreCase(String nome);
}
