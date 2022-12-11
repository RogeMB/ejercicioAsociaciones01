package com.salesianostriana.ejercicio01Asociaciones.service;

import com.salesianostriana.ejercicio01Asociaciones.model.Producto;
import com.salesianostriana.ejercicio01Asociaciones.repository.ProductoRepository;
import com.salesianostriana.ejercicio01Asociaciones.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ProductoService extends BaseService<Producto, Long, ProductoRepository> {
}
