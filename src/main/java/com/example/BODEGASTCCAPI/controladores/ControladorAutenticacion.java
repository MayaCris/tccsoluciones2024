package com.example.BODEGASTCCAPI.controladores;

import com.example.BODEGASTCCAPI.helpers.validacionJWT.JwtUtil;
import com.example.BODEGASTCCAPI.modelos.Usuario;
import com.example.BODEGASTCCAPI.modelos.dto.SolicitudInicioSesion;
import com.example.BODEGASTCCAPI.servicios.AutenticacionUsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class ControladorAutenticacion {

    private final AutenticacionUsuarioServicio autenticacionUsuarioServicio;
    private final JwtUtil jwtUtil;

    public ControladorAutenticacion(AutenticacionUsuarioServicio autenticacionUsuarioServicio, JwtUtil jwtUtil) {
        this.autenticacionUsuarioServicio = autenticacionUsuarioServicio;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    @Operation(
            summary = "Autenticación de usuario",
            description = "Permite autenticar un usuario en el sistema"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuario autenticado con exito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SolicitudInicioSesion.class),
                                    examples = @ExampleObject(value = "{\"Correo\":\"mail@mail.com\",\"Contraseña\":\"********\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al autenticar el usuario",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"Error al autenticar el usuario\"}")
                            )
                    )
            }
    )
    public ResponseEntity<?> login(@RequestBody SolicitudInicioSesion solicitudInicioSesion){
        if (autenticacionUsuarioServicio.autenticarUsuario(solicitudInicioSesion)){
            String token = jwtUtil.generarToken(solicitudInicioSesion.getCorreo());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    @PostMapping("/register")
    @Operation(
            summary = "Registro de un usuario",
            description = "Permite registrar un usuario en el sistema"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuario registrado con exito",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SolicitudInicioSesion.class),
                                    examples = @ExampleObject(value = "{\"Nombre\":\"Pepito Perez\",\"Correo\":\"mail@mail.com\",\"Contraseña\":\"********\",\"Rol\":\"Administrador\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error al registrar un usuario",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class),
                                    examples = @ExampleObject(value = "{\"mensaje\":\"Error al registrar un usuario\"}")
                            )
                    )
            }
    )
    public ResponseEntity<?> registro(@RequestBody Usuario usuario){
        try {
            return ResponseEntity.ok(autenticacionUsuarioServicio.registrarUsuario(usuario));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
