package dw2.locadora.controller;

import dw2.locadora.model.Dependente;
import dw2.locadora.service.DependenteService;
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
@RequestMapping("/dependente")
public class DependenteController {
    private final DependenteService dependenteService;

    @PostMapping
    public ResponseEntity<Dependente> save(@RequestBody Dependente dependente,
                                           HttpServletResponse response) throws Exception {
        try {
            Dependente persistedDependente = dependenteService.save(dependente);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                    .buildAndExpand(persistedDependente.getId()).toUri();
            response.setHeader("Location", uri.toASCIIString());
            return ResponseEntity.created(uri).body(persistedDependente);
        } catch (DataIntegrityViolationException e) {
            throw new Exception();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dependente> update(@RequestBody Dependente dependente,
                                             @PathVariable Long id) {
        try {
            Dependente updatedDependente = dependenteService.update(id, dependente);
            if (updatedDependente == null) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(updatedDependente);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Dependente");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dependenteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dependente> getById(@PathVariable Long id) {
        Dependente research = dependenteService.getById(id);
        if (research == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(research);
    }

    @GetMapping
    public ResponseEntity<List<Dependente>> get() {
        List<Dependente> dependentes = dependenteService.get();
        if (dependentes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dependentes);
    }
}
