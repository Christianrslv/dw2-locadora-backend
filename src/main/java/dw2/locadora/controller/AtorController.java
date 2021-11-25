package dw2.locadora.controller;

import dw2.locadora.model.Ator;
import dw2.locadora.service.AtorService;
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
@RequestMapping("/ator")
public class AtorController {
    private final AtorService atorService;

    @PostMapping
    public ResponseEntity<Ator> save(@RequestBody Ator ator,
                                     HttpServletResponse response) throws Exception {
        try {
            Ator persistedAtor = atorService.save(ator);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                    .buildAndExpand(persistedAtor.getId()).toUri();
            response.setHeader("Location", uri.toASCIIString());
            return ResponseEntity.created(uri).body(persistedAtor);
        } catch (DataIntegrityViolationException e) {
            throw new Exception();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ator> update(@RequestBody Ator ator,
                                       @PathVariable Long id) {
        try {
            Ator updatedAtor = atorService.update(id, ator);
            if (updatedAtor == null) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(updatedAtor);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Ator");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> getById(@PathVariable Long id) {
        Ator research = atorService.getById(id);
        if (research == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(research);
    }

    @GetMapping
    public ResponseEntity<List<Ator>> get() {
        List<Ator> ators = atorService.get();
        if (ators.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(ators);
    }
}
