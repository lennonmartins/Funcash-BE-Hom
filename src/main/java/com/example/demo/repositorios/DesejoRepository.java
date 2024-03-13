package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelos.Desejo;

public interface DesejoRepository extends JpaRepository<Desejo, Long>{
    List<Desejo>findByTituloContainingIgnoreCase(String titulo);
}
