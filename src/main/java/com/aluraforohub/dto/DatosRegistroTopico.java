package com.aluraforohub.dto;

import com.aluraforohub.model.Curso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter

public record DatosRegistroTopico(@NotBlank
                                  String titulo,
                                  @NotBlank
                                  String mensaje,
                                  @NotNull @Valid
                                  Curso curso) {
}
