package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.repositorios.IZonaBodegaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaBodegaServicio {

    @Autowired
    IZonaBodegaRepositorio zonaBodegaRepositorio;

    //Guardar
    public ZonaBodega almacenarZonaBodega(ZonaBodega datosZonaBodega){
        return zonaBodegaRepositorio.save(datosZonaBodega);
    }
    //Buscar todos las zonas de bodega
    public List<ZonaBodega> buscarTodasZonasBodega(){
        return zonaBodegaRepositorio.findAll();
    }

    //Buscar por id
    public ZonaBodega buscarZonaBodegaPorId(Long id){
        return zonaBodegaRepositorio.findById(id).orElse(null);
    }

    //Buscar por nombre
    public ZonaBodega buscarZonaBodegaPorNombre(String nombre){
        return null;
    }

    //Modificar
    public ZonaBodega modificarZonaBodega(Long id, ZonaBodega datosNuevosZonaBodega){
        return null;
    }

    //Eliminar
    public boolean eliminarZonaBodega(Long id){
        try {
            zonaBodegaRepositorio.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
