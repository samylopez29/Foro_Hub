package com.aluraforohub.dto;

import com.aluraforohub.model.Curso;
import com.aluraforohub.model.Topico;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        Curso curso) {
    public DatosListaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getCurso());
    }
}
