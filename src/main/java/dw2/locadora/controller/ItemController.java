package dw2.locadora.controller;

import dw2.locadora.model.Item;
import dw2.locadora.service.ItemService;
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
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> save(@RequestBody Item item,
                                     HttpServletResponse response) throws Exception {
        try {
            Item persistedItem = itemService.save(item);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                    .buildAndExpand(persistedItem.getId()).toUri();
            response.setHeader("Location", uri.toASCIIString());
            return ResponseEntity.created(uri).body(persistedItem);
        } catch (DataIntegrityViolationException e) {
            throw new Exception();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@RequestBody Item item,
                                       @PathVariable Long id) {
        try {
            Item updatedItem = itemService.update(id, item);
            if (updatedItem == null) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(updatedItem);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceNotFoundException("Item");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable Long id) {
        Item research = itemService.getById(id);
        if (research == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(research);
    }

    @GetMapping
    public ResponseEntity<List<Item>> get() {
        List<Item> items = itemService.get();
        if (items.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(items);
    }
}
