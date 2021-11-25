package dw2.locadora.controller;

import dw2.locadora.model.Socio;
import dw2.locadora.service.SocioService;
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
@RequestMapping("/socio")
public class SocioController {
    private final SocioService socioService;

    @PostMapping
    public ResponseEntity<Socio> save(@RequestBody Socio socio,
                                      HttpServletResponse response) throws Exception {
        try {
            Socio persistedSocio = socioService.save(socio);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                    .buildAndExpand(persistedSocio.getId()).toUri();
            response.setHeader("Location", uri.toASCIIString());
            return ResponseEntity.created(uri).body(persistedSocio);
        } catch (DataIntegrityViolationException e) {
            throw new Exception();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Socio> update(@RequestBody Socio socio,
                                        @PathVariable Long id) {
        try {
            Socio updatedSocio = socioService.update(id, socio);
            if (updatedSocio == null) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(updatedSocio);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Socio");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        socioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Socio> getById(@PathVariable Long id) {
        Socio research = socioService.getById(id);
        if (research == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(research);
    }

    @GetMapping
    public ResponseEntity<List<Socio>> get() {
        List<Socio> socios = socioService.get();
        if (socios.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(socios);
    }
}
