package com.example.demo.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modelos.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
    Optional<Usuario> obterPorEmailESenha(@Param("email") String email, @Param("senha") String senha);

    @Query("SELECT u FROM Usuario u JOIN u.responsavel r WHERE r.id = :idDoResponsavel")
    Usuario findByIdResponsavel(Long idDoResponsavel);

    //@Query("SELECT u FROM Usuario u where u.email = :email")
    Optional<Usuario> findByEmail(@Param("email") String email);
}
