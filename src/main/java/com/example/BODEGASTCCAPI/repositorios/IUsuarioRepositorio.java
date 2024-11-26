package com.example.BODEGASTCCAPI.repositorios;

import com.example.BODEGASTCCAPI.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByCorreo(String correo);
}
