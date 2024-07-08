package com.literatura.Literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String isbn;
    private String genero;

    public Object getId(Object titulo) {
    }

    public Object getTitulo() {
    }

    public long getAutor() {
    }
}
