package com.salesianostriana.ejercicio01Asociaciones.repository;

import com.salesianostriana.ejercicio01Asociaciones.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
