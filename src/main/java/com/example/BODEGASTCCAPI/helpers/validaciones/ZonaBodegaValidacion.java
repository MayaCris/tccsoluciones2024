package com.example.BODEGASTCCAPI.helpers.validaciones;

import org.springframework.stereotype.Component;

@Component
public class ZonaBodegaValidacion {

    public boolean validarNombre(String zonaBodegaNombre){
        if (zonaBodegaNombre.length() > 50){
            return false;
        }
        else return(!zonaBodegaNombre.matches("^[a-zA-Z\\s]+$"));
    }

    public boolean validarCapacidadMaximaVolumen(Double zonaBodegaCapacidadMaximaVolumen){
        if (zonaBodegaCapacidadMaximaVolumen < 0){
            return false;
        }
        else return true;
    }

    public boolean validarCapacidadMaximaPeso(Double zonaBodegaCapacidadMaximaPeso){
        if(zonaBodegaCapacidadMaximaPeso < 0){
            return false;
        }
        else return true;
    }

    public boolean validarCapacidadVolumenOcupado(Double zonaBodegaCapacidadVolumenOcupado){
        if(zonaBodegaCapacidadVolumenOcupado < 0){
            return false;
        }
        else return true;
    }

    public boolean validarCapacidadPesoOcupado(Double zonaBodegaCapacidadPesoOcupado){
        if(zonaBodegaCapacidadPesoOcupado < 0) {
            return false;
        }
        else return true;
    }
}

