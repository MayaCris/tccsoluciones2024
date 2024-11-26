package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.helpers.mensajes.Mensaje;
import com.example.BODEGASTCCAPI.modelos.Usuario;
import com.example.BODEGASTCCAPI.modelos.dto.SolicitudInicioSesion;
import com.example.BODEGASTCCAPI.modelos.mapas.IMapaInicioSesion;
import com.example.BODEGASTCCAPI.repositorios.IUsuarioRepositorio;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AutenticacionUsuarioServicio {

    @Autowired
    IUsuarioRepositorio usuarioRepositorio;

    public boolean autenticarUsuario(SolicitudInicioSesion solicitudInicioSesion) {
        return usuarioRepositorio.findByCorreo(solicitudInicioSesion.getCorreo())
                .map(usuario -> validarCredenciales(solicitudInicioSesion))
                .orElse(false);

    }

    private boolean validarCredenciales(SolicitudInicioSesion solicitudInicioSesion) {
        if (solicitudInicioSesion.getContrasena()
                .equals(usuarioRepositorio.findByCorreo(solicitudInicioSesion.getCorreo())
                        .map(Usuario::getContrasena).orElse(""))) {
            System.out.println("Usuario autenticado correctamente");
            return true;
        } else {
            System.out.println("Contraseña incorrecta");
            return false;
        }
    }

    public Usuario registrarUsuario(Usuario usuario) throws ValidationException{
        if (validarUsuarioCreado(usuario)){
            throw new ValidationException(Mensaje.USUARIO_EXISTENTE.getMensaje());
        }
        return usuarioRepositorio.save(usuario);
    }

    public boolean validarUsuarioCreado(Usuario usuario){
       return usuarioRepositorio.findByCorreo(usuario.getCorreo()).isPresent();
    }
}
