package com.example.BODEGASTCCAPI.servicios;

import com.example.BODEGASTCCAPI.helpers.mensajes.Mensaje;
import com.example.BODEGASTCCAPI.helpers.validaciones.MercanciaValidacion;
import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.modelos.dto.MercanciaDTO;
import com.example.BODEGASTCCAPI.modelos.mapas.IMapaMercancia;
import com.example.BODEGASTCCAPI.repositorios.IMercanciaRepositorio;
import jakarta.validation.ValidationException;
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

    @Autowired
    IMapaMercancia mapaMercancia;

    //guardar
    public Mercancia almacenarMercancia(Mercancia datosMercancia) throws ValidationException {

            //aplicar validaciones a los datos recibidos
            //Si sale bien la validacion llamo al repositorio para guardar los datos
            if (!this.mercanciaValidacion.validarNombre(datosMercancia.getNombre())){
                throw new ValidationException(Mensaje.NOMBRE_INVALIDO.getMensaje());
            }
            if (!this.mercanciaValidacion.validarPeso(datosMercancia.getPeso())){
                throw new ValidationException(Mensaje.PESO_NEGATIVO.getMensaje());
            }
            if (!this.mercanciaValidacion.validarVolumen(datosMercancia.getVolumen())){
                throw new ValidationException(Mensaje.VOLUMEN_NEGATIVO.getMensaje());
            }
            if (!this.mercanciaValidacion.validarFechaIngreso(datosMercancia.getFechaIngreso(), datosMercancia.getFechaSalida())){
                throw new ValidationException(Mensaje.FECHA_INGRESO_INVALIDA.getMensaje());
            }
            return mercanciaRepositorio.save(datosMercancia);
    }



    public MercanciaDTO almacenarMercanciaDTO(Mercancia datosMercancia) throws ValidationException {

        //aplicar validaciones a los datos recibidos
        //si sale bien la validacion llamo al repo para guardar los datos
        if (!this.mercanciaValidacion.validarNombre(datosMercancia.getNombre())){
            throw new ValidationException(Mensaje.NOMBRE_INVALIDO.getMensaje());
        }

        if(!this.mercanciaValidacion.validarPeso(datosMercancia.getPeso())){
            throw new ValidationException(Mensaje.PESO_NEGATIVO.getMensaje());
        }

        if(!this.mercanciaValidacion.validarVolumen(datosMercancia.getVolumen())){
            throw new ValidationException(Mensaje.VOLUMEN_NEGATIVO.getMensaje());
        }

        if(!this.mercanciaValidacion.validarFechaIngreso(datosMercancia.getFechaIngreso(), LocalDate.now())){
            throw new ValidationException(Mensaje.FECHA_INGRESO_INVALIDA.getMensaje());
        }
        //TODO AVERIGUAR SI LA ZONA DONDE LA MERCANCIA SE VA A GUARDAR TIENE ESPACIO DISPONIBLE
        return this.mapaMercancia.mapearMercancia(this.mercanciaRepositorio.save(datosMercancia));

    }


    //buscar todos
    public List<MercanciaDTO> buscarTodasMercancias() throws Exception{
        try{
            return this.mapaMercancia.mapearListaMercancias(mercanciaRepositorio.findAll());
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
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
