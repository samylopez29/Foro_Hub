package com.aluraforohub.dto;

import lombok.Getter;

@Getter

public record DatosAutenticacion(String login, String password) {
}
