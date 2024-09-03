package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.repositorios.IMercanciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MercanciaServicio {
    //Los servicios contienen metodos asociados a alguna operacion en la base de datos. Generalmente están asociados a uno  o mas repositorios.

    //Inyectar una dependencia al repositorio
    @Autowired
    IMercanciaRepositorio mercanciaRepositorio;

    //guardar
    public Mercancia almacenarMercancia(Mercancia datosMercancia) throws Exception {
        try {
            //aplicar validaciones a los datos recibidos
            //Si sale bien la validacion llamo al repositorio para guardar los datos
            return mercanciaRepositorio.save(datosMercancia);
            }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //buscar todos
    public List<Mercancia> buscarTodasMercancias(){
        return mercanciaRepositorio.findAll();
    }

    //buscar por id
    public Mercancia buscarMercanciaPorId(UUID id){
        return mercanciaRepositorio.findById(id).orElse(null);
    }

    //buscar nombre
    public Mercancia buscarMercanciaPorNombre(String nombre){
        return null;
    }

    //modificar
    public Mercancia modificarMercancia(UUID id, Mercancia datosNuevosMercancia){
        return null;
    }

    //eliminar
    public boolean eliminarMercancia(UUID id){
        try {
            mercanciaRepositorio.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }



}
