package com.salesianostriana.ejercicio01Asociaciones.repository;

import com.salesianostriana.ejercicio01Asociaciones.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
