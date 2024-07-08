package com.literatura.Literatura.controller;

import com.literatura.Literatura.model.Libro;
import com.literatura.Literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        return libroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroRepository.save(libro);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(nuevoLibro.getId(libro.getTitulo())).toUri();
        return ResponseEntity.created(location).body(nuevoLibro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return libroRepository.findById(id)
                .map(libroExistente -> {
                    libroExistente.getId(libro.getTitulo());
                    try {
                        libroExistente.wait(libro.getAutor());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    // ... actualizar otros campos
                    libroRepository.save(libroExistente);
                    return ResponseEntity.ok(libroExistente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}