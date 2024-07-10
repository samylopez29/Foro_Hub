package com.aluraforohub.dto;

import lombok.Getter;

@Getter

public record DatosActualizarTopico(Long Id,
                                    String titulo,
                                    String mensaje) {
}
