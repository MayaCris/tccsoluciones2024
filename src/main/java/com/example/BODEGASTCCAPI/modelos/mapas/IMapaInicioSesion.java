package com.example.BODEGASTCCAPI.modelos.mapas;

import com.example.BODEGASTCCAPI.modelos.dto.SolicitudInicioSesion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IMapaInicioSesion {
    @Mappings({
        @Mapping(source = "correo", target = "correo"),
        @Mapping(source = "contrasena", target = "contrasena")
    })
    public SolicitudInicioSesion mapearSolicitudInicioSesion(SolicitudInicioSesion solicitudInicioSesion);


}
