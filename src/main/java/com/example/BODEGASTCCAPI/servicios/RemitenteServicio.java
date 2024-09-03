package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.modelos.Remitente;
import com.example.BODEGASTCCAPI.repositorios.IRemitenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemitenteServicio {

    @Autowired
    IRemitenteRepositorio remitenteRepositorio;
    //Guardar
    public Remitente almacenarRemitente(Remitente datosRemitente){
        return remitenteRepositorio.save(datosRemitente);
    }

    //Buscar todos los remitentes
    public List<Remitente> buscarTodosRemitentes(){
        return remitenteRepositorio.findAll();
    }

    //Buscar por id
    public Remitente buscarRemitentePorId(Long id){
        return remitenteRepositorio.findById(id).orElse(null);
    }
    //Buscar por nombre
    public Remitente buscarRemitentePorNombre(String nombre){
        return null;
    }
    //Modificar
    public Remitente modificarRemitente(Long id, Remitente datosNuevosRemitente){
        return null;
    }
    //Eliminar
    public boolean eliminarRemitente(Long id){
        try {
            remitenteRepositorio.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
