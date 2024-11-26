package com.example.BODEGASTCCAPI.helpers.validacionJWT;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "Contraseña";
    private final String ISSUER = "BodegasTCC";

    public String generarToken (String usuario){
        return JWT.create()
                .withSubject(usuario)
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) //1 hora expresada en milisegundos
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String validarToken(String token) {
        try{
            Algorithm algoritmo = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verificador = JWT.require(algoritmo)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT jwtDecodificado = verificador.verify(token);
            return jwtDecodificado.getSubject();
        } catch (JWTVerificationException error) {
            throw new RuntimeException("Token inválido o expirado");
        }
    }

}
