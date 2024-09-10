package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.Mercancia;
import com.example.BODEGASTCCAPI.servicios.MercanciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/soluciontcc/v1/mercancia")
public class ControladorMercancia {
    //Llamar al servicio para
    //Inyectar una dependencia al servicio

    @Autowired
    MercanciaServicio mercanciaServicio;

    //Llamar cada uno de los metodos disponibles en el servicio

    public ResponseEntity<?> almacenarMercancia(@RequestBody Mercancia datosMercancia){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.mercanciaServicio.almacenarMercancia(datosMercancia));

        } catch (Exception e) {

            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(mensajeRespuesta);
        }
    }







}
