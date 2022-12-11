package com.salesianostriana.ejercicio01Asociaciones;

import com.salesianostriana.ejercicio01Asociaciones.model.Categoria;
import com.salesianostriana.ejercicio01Asociaciones.model.Producto;
import com.salesianostriana.ejercicio01Asociaciones.service.CategoriaService;
import com.salesianostriana.ejercicio01Asociaciones.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MainDeMentira {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @PostConstruct
    public void test() {
        Categoria jazzBass = Categoria.builder()
                .nombre("JazzBass")
                .categoriaPadre(null)
                .build();

        categoriaService.save(jazzBass);

        Categoria precisionBass = Categoria.builder()
                .nombre("PrecisionBass")
                .categoriaPadre(null)
                .build();

        categoriaService.save(precisionBass);

        Categoria jbFourString = Categoria.builder()
                .nombre("JazzBass FourString")
                .categoriaPadre(jazzBass)
                .build();

        categoriaService.save(jbFourString);

        Categoria jbFiveString = Categoria.builder()
                .nombre("JazzBass FiveString")
                .categoriaPadre(jazzBass)
                .build();

        categoriaService.save(jbFiveString);


        Categoria pbFourString = Categoria.builder()
                .nombre("PrecisionBass FourString")
                .categoriaPadre(precisionBass)
                .build();

        categoriaService.save(pbFourString);

        Categoria pbFiveString = Categoria.builder()
                .nombre("PrecisionBass FiveString")
                .categoriaPadre(precisionBass)
                .build();

        categoriaService.save(pbFiveString);

        Producto sadowskyVintageFour = Producto.builder()
                .nombre("Sadowsky Vintage Four")
                .pvp(2600.99)
                .categoria(jbFourString)
                .build();

        productoService.save(sadowskyVintageFour);

        Producto sadowskyMetroFive = Producto.builder()
                .nombre("Sadowsky Metro Five")
                .pvp(2200.99)
                .categoria(pbFiveString)
                .build();

        productoService.save(sadowskyMetroFive);

        // productoService.delete(sadowskyMetroFive);
        // categoriaService.delete(jbFiveString);


        if(!jbFourString.getProductoList().isEmpty()){
            System.out.println("Categoria: " + jbFourString.getNombre());
            jbFourString.getProductoList().forEach(System.out::println);
        }

    }


}