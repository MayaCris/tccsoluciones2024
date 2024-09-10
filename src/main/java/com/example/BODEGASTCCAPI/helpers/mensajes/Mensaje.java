package com.example.BODEGASTCCAPI.helpers.mensajes;

public enum Mensaje {
    PESO_NEGATIVO("El peso no puede ser menor o igual a 0"),
    VOLUMEN_NEGATIVO("El volumen no puede ser menor o igual a 0"),
    FECHA_INGRESO_INVALIDA("La fecha de ingreso no puede ser mayor a la fecha actual"),


    ;
    private String mensaje;

    Mensaje() {
    }

    Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }


}
