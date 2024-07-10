package com.aluraforohub.dto;

import com.aluraforohub.model.Curso;
import com.aluraforohub.model.Topico;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter

public record DatosCreacionTopico(String titulo,
                                  String mensaje,
                                  LocalDateTime fechaCreacion,
                                  Curso curso) {
    public DatosCreacionTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getCurso());
    }
}
