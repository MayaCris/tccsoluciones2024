package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.modelos.Remitente;
import com.example.BODEGASTCCAPI.servicios.RemitenteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/soluciontcc/v1/remitente")
public class ControladorRemitente {

    @Autowired
    RemitenteServicio remitenteServicio;

    @PostMapping()
    public ResponseEntity<?> almacenarRemitente(@RequestBody Remitente datosRemitente){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.remitenteServicio.almacenarRemitente(datosRemitente));

        } catch (Exception e) {
            HashMap<String, Object> mensajeRespuesta = new HashMap<>();
            mensajeRespuesta.put("mensaje", e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(mensajeRespuesta);
        }
    }
}
