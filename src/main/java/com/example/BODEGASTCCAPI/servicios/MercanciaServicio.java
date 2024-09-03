package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.helpers.validaciones.MercanciaValidacion;
import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.repositorios.IMercanciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MercanciaServicio {
    //Los servicios contienen metodos asociados a alguna operacion en la base de datos. Generalmente están asociados a uno  o mas repositorios.

    //Inyectar una dependencia al repositorio
    @Autowired
    IMercanciaRepositorio mercanciaRepositorio;

    @Autowired
    MercanciaValidacion mercanciaValidacion;

    //guardar
    public Mercancia almacenarMercancia(Mercancia datosMercancia) throws Exception {
        try {
            //aplicar validaciones a los datos recibidos
            //Si sale bien la validacion llamo al repositorio para guardar los datos
            if (!this.mercanciaValidacion.validarPeso(datosMercancia.getPeso())){
                throw new Exception("El peso no puede ser menor o igual a 0");
            }
            if (!this.mercanciaValidacion.validarVolumen(datosMercancia.getVolumen())){
                throw new Exception("El volumen no puede ser menor o igual a 0");
            }
            if (!this.mercanciaValidacion.validarFechaIngreso(datosMercancia.getFechaIngreso(), LocalDate.now())){
                throw new Exception("La fecha de ingreso no puede ser mayor a la fecha actual");
            }
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
