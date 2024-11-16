package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.helpers.mensajes.Mensaje;
import com.example.BODEGASTCCAPI.helpers.validaciones.ZonaBodegaValidacion;
import com.example.BODEGASTCCAPI.modelos.ZonaBodega;
import com.example.BODEGASTCCAPI.repositorios.IZonaBodegaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ZonaBodegaServicio {

    @Autowired
    IZonaBodegaRepositorio zonaBodegaRepositorio;
    @Autowired
    ZonaBodegaValidacion zonaBodegaValidacion;

    //Guardar
    public ZonaBodega almacenarZonaBodega(ZonaBodega datosZonaBodega) throws Exception{
        try {
            if (datosZonaBodega.getCapacidadPesoOcupado()== null){
                datosZonaBodega.setCapacidadPesoOcupado(0.0);
            }
            if (datosZonaBodega.getCapacidadVolumenOcupado()== null){
                datosZonaBodega.setCapacidadVolumenOcupado(0.0);
            }
            if (Objects.equals(datosZonaBodega.getNombreZona(), buscarTodasZonasBodega().stream().filter(zonaBodega -> zonaBodega.getNombreZona().equals(datosZonaBodega.getNombreZona())).findFirst().get().getNombreZona())){
                throw new Exception(Mensaje.NOMBRE_BODEGA_DUPLICADO.getMensaje());
            }
            if (!this.zonaBodegaValidacion.validarNombre(datosZonaBodega.getNombreZona())){
                throw new Exception(Mensaje.NOMBRE_BODEGA.getMensaje());
            }
            if (!this.zonaBodegaValidacion.validarCapacidadMaximaVolumen(datosZonaBodega.getCapacidadMaximaVolumen())){
                throw new Exception(Mensaje.CAPACIDAD_MAXIMA_VOLUMEN.getMensaje());
            }
            if (!this.zonaBodegaValidacion.validarCapacidadMaximaPeso(datosZonaBodega.getCapacidadMaximaPeso())){
                throw new Exception(Mensaje.CAPACIDAD_MAXIMA_PESO.getMensaje());
            }
            if (!this.zonaBodegaValidacion.validarCapacidadVolumenOcupado(datosZonaBodega.getCapacidadVolumenOcupado(), datosZonaBodega.getCapacidadMaximaVolumen())){
                throw new Exception(Mensaje.CAPACIDAD_VOLUMEN_OCUPADO.getMensaje());
            }
            if (!this.zonaBodegaValidacion.validarCapacidadPesoOcupado(datosZonaBodega.getCapacidadPesoOcupado(), datosZonaBodega.getCapacidadMaximaPeso())){
                throw new Exception(Mensaje.CAPACIDAD_PESO_OCUPADO.getMensaje());
            }
            return zonaBodegaRepositorio.save(datosZonaBodega);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    //Buscar todas las zonas de bodega
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
