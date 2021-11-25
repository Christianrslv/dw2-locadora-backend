package dw2.locadora.controller;

import dw2.locadora.model.Locacao;
import dw2.locadora.service.LocacaoService;
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
@RequestMapping("/locacao")
public class LocacaoController {
    private final LocacaoService locacaoService;

    @PostMapping
    public ResponseEntity<Locacao> save(@RequestBody Locacao locacao,
                                        HttpServletResponse response) throws Exception {
        try {
            Locacao persistedLocacao = locacaoService.save(locacao);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                    .buildAndExpand(persistedLocacao.getId()).toUri();
            response.setHeader("Location", uri.toASCIIString());
            return ResponseEntity.created(uri).body(persistedLocacao);
        } catch (DataIntegrityViolationException e) {
            throw new Exception();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locacao> update(@RequestBody Locacao locacao,
                                          @PathVariable Long id) {
        try {
            Locacao updatedLocacao = locacaoService.update(id, locacao);
            if (updatedLocacao == null) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(updatedLocacao);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Locacao");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        locacaoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locacao> getById(@PathVariable Long id) {
        Locacao research = locacaoService.getById(id);
        if (research == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(research);
    }

    @GetMapping
    public ResponseEntity<List<Locacao>> get() {
        List<Locacao> locacaos = locacaoService.get();
        if (locacaos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(locacaos);
    }
}
