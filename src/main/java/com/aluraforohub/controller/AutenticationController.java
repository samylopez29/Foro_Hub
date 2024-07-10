package com.aluraforohub.controller;

import com.aluraforohub.dto.DatosAutenticacion;
import com.aluraforohub.dto.DatosTokenJWT;
import com.aluraforohub.model.Usuario;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Getter

public class AutenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DatosAutenticacion datosAutenticacion) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.login(), datosAutenticacion.password());
        var autenticacion = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}

