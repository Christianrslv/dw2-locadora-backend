package dw2.locadora.controller;

import dw2.locadora.model.Diretor;
import dw2.locadora.service.DiretorService;
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
@RequestMapping("/diretor")
public class DiretorController {
    private final DiretorService diretorService;

    @PostMapping
    public ResponseEntity<Diretor> save(@RequestBody Diretor diretor,
                                        HttpServletResponse response) throws Exception {
        try {
            Diretor persistedDiretor = diretorService.save(diretor);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                    .buildAndExpand(persistedDiretor.getId()).toUri();
            response.setHeader("Location", uri.toASCIIString());
            return ResponseEntity.created(uri).body(persistedDiretor);
        } catch (DataIntegrityViolationException e) {
            throw new Exception();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diretor> update(@RequestBody Diretor diretor,
                                          @PathVariable Long id) {
        try {
            Diretor updatedDiretor = diretorService.update(id, diretor);
            if (updatedDiretor == null) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(updatedDiretor);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Diretor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        diretorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> getById(@PathVariable Long id) {
        Diretor research = diretorService.getById(id);
        if (research == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(research);
    }

    @GetMapping
    public ResponseEntity<List<Diretor>> get() {
        List<Diretor> diretors = diretorService.get();
        if (diretors.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(diretors);
    }
}
