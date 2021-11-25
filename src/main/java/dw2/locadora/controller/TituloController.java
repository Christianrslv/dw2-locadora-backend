package dw2.locadora.controller;

import dw2.locadora.model.Titulo;
import dw2.locadora.service.TituloService;
import dw2.locadora.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/titulo")
public class TituloController {
    private final TituloService tituloService;

    @PostMapping
    public ResponseEntity<Titulo> save(@RequestBody Titulo titulo,
                                       HttpServletResponse response) throws Exception {
        try {
            Titulo persistedTitulo = tituloService.save(titulo);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                    .buildAndExpand(persistedTitulo.getId()).toUri();
            response.setHeader("Location", uri.toASCIIString());
            return ResponseEntity.created(uri).body(persistedTitulo);
        } catch (DataIntegrityViolationException e) {
            throw new Exception();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Titulo> update(@RequestBody Titulo titulo,
                                         @PathVariable Long id) {
        try {
            Titulo updatedTitulo = tituloService.update(id, titulo);
            if (updatedTitulo == null) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(updatedTitulo);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Titulo");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tituloService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Titulo> getById(@PathVariable Long id) {
        Titulo research = tituloService.getById(id);
        if (research == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(research);
    }

    @GetMapping
    public ResponseEntity<List<Titulo>> get() {
        List<Titulo> titulos = tituloService.get();
        if (titulos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(titulos);
    }
}
