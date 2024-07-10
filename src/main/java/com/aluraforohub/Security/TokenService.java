package com.aluraforohub.Security;


import com.aluraforohub.model.Usuario;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.springframework.security.config.Elements.JWT;


@Service
@Getter
public class TokenService {

    @Value("${api.security.secret}")
    private String secret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("foro-hub")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException jwtCreationException) {
            throw new RuntimeException("Error al generar el token", jwtCreationException);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo).withIssuer("foro-hub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT invalido o expirado!");
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }
}