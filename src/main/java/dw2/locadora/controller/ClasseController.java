package dw2.locadora.controller;

import dw2.locadora.model.Classe;
import dw2.locadora.service.ClasseService;
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
@RequestMapping("/classe")
public class ClasseController {
    private final ClasseService classeService;

    @PostMapping
    public ResponseEntity<Classe> save(@RequestBody Classe classe,
                                       HttpServletResponse response) throws Exception {
        try {
            Classe persistedClasse = classeService.save(classe);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                    .buildAndExpand(persistedClasse.getId()).toUri();
            response.setHeader("Location", uri.toASCIIString());
            return ResponseEntity.created(uri).body(persistedClasse);
        } catch (DataIntegrityViolationException e) {
            throw new Exception();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classe> update(@RequestBody Classe classe,
                                         @PathVariable Long id) {
        try {
            Classe updatedClasse = classeService.update(id, classe);
            if (updatedClasse == null) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(updatedClasse);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Classe");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> getById(@PathVariable Long id) {
        Classe research = classeService.getById(id);
        if (research == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(research);
    }

    @GetMapping
    public ResponseEntity<List<Classe>> get() {
        List<Classe> classes = classeService.get();
        if (classes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(classes);
    }
}
