package com.aluraforohub.controller;

import com.aluraforohub.Repository.TopicoRepository;
import com.aluraforohub.dto.DatosActualizarTopico;
import com.aluraforohub.dto.DatosCreacionTopico;
import com.aluraforohub.dto.DatosListaTopico;
import com.aluraforohub.dto.DatosRegistroTopico;
import com.aluraforohub.model.Topico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                          UriComponentsBuilder uriComponentsBuilder) {
        var topico = new Topico(datosRegistroTopico);
        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosCreacionTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> datosListaTopicos(@PageableDefault(size = 5, sort = {"curso"}) Pageable pageable) {
        var page = topicoRepository.findAll(pageable).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        var topico = topicoRepository.getReferenceById(datosActualizarTopico.Id());
        topico.actualizarInformacion(datosActualizarTopico);
        return ResponseEntity.ok(new DatosCreacionTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).orElse(null);
        if (topico == null) {
            return ResponseEntity.noContent().build();
        }
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosCreacionTopico(topico));
    }
}