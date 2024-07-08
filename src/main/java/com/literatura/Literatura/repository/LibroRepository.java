package com.literatura.Literatura.repository;


import com.literatura.Literatura.model.Libro;
import io.gun.spring.repository.GunJpaRepository; // Nueva ruta de importación
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>, GunJpaRepository<Libro, Long> {
    // Puedes agregar métodos de búsqueda personalizados aquí si es necesario
}
